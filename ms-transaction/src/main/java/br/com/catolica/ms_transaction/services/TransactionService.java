package br.com.catolica.ms_transaction.services;


import br.com.catolica.ms_transaction.client.BookTransaction;
import br.com.catolica.ms_transaction.client.UserTransaction;
import br.com.catolica.ms_transaction.config.RabbitMQConfig;
import br.com.catolica.ms_transaction.domain.Transaction;
import br.com.catolica.ms_transaction.dto.TransactionDTO;
import br.com.catolica.ms_transaction.dto.response.BookDTO;
import br.com.catolica.ms_transaction.dto.response.UserDTO;
import br.com.catolica.ms_transaction.mapper.TransactionMapper;
import br.com.catolica.ms_transaction.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final BookTransaction bookTransaction;
    private final UserTransaction userTransaction;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;

    public TransactionDTO save(@RequestBody TransactionDTO transactionDTO) {

        if (transactionDTO.getBookId() == null || transactionDTO.getUserId() == null) {
            throw new IllegalArgumentException("Os ids do livro e do usuário são obrigatórios!");
        }

        BookDTO book = bookTransaction.getBookById(transactionDTO.getBookId());
        UserDTO user = userTransaction.getUserById(transactionDTO.getUserId());

        if (book == null) {
            throw new IllegalStateException("Livro não encontrado com o ID: " + transactionDTO.getBookId());
        }

        if (user == null) {
            throw new IllegalStateException("Usuário não encontrado com o ID: " + transactionDTO.getUserId());
        }

         transactionDTO.setLoanDate(LocalDateTime.now());

        Transaction transaction = transactionMapper.dtoToEntity(transactionDTO);
        rabbitTemplate.convertAndSend(rabbitMQConfig.getEmailExchangeName(), rabbitMQConfig.getEmailRoutingKey(), user.getEmail());
        transactionRepository.save(transaction);
        return transactionMapper.entityToDTO(transaction);
    }


    public List<TransactionDTO> getAll() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::entityToDTO)
                .toList();
    }

    public TransactionDTO verifyFine(Long id) throws NullPointerException {
        var transaction = transactionRepository.findById(id).orElse(null);

        if (transaction == null) {
            throw new NullPointerException("Movimentação não foi encontrada");
        }

        LocalDateTime term = transaction.getLoanDate().plusDays(3);
        LocalDateTime currentDate = LocalDateTime.now();

        if (term.isBefore(currentDate)) {
            Period difference = Period.between(term.toLocalDate(), currentDate.toLocalDate());
            int totalDays = difference.getYears() * 365 + difference.getMonths() * 30 + difference.getDays();
            double fineValue = 1.00 * totalDays;

            transaction.setFineValue(BigDecimal.valueOf(fineValue));
            transaction.setReturnDate(currentDate);

            transactionRepository.save(transaction);
        }

        return transactionMapper.entityToDTO(transaction);
    }

    public List<TransactionDTO> delayedMovements() {

        List<Transaction> movementsWithoutReturn = transactionRepository
                .findByReturnDateIsNullOrReturnDateBefore(LocalDateTime.now());

        return movementsWithoutReturn
                .stream()
                .map(transactionMapper::entityToDTO)
                .toList();
    }
}
