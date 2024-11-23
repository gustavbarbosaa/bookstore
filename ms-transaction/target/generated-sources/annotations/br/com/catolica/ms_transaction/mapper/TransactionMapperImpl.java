package br.com.catolica.ms_transaction.mapper;

import br.com.catolica.ms_transaction.domain.Transaction;
import br.com.catolica.ms_transaction.dto.TransactionDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-23T08:38:21-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction dtoToEntity(TransactionDTO transactionDTO) {
        if ( transactionDTO == null ) {
            return null;
        }

        Transaction.TransactionBuilder transaction = Transaction.builder();

        transaction.userId( transactionDTO.getUserId() );
        transaction.bookId( transactionDTO.getBookId() );
        transaction.loanDate( transactionDTO.getLoanDate() );
        transaction.returnDate( transactionDTO.getReturnDate() );
        transaction.fineValue( transactionDTO.getFineValue() );

        return transaction.build();
    }

    @Override
    public TransactionDTO entityToDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDTO.TransactionDTOBuilder transactionDTO = TransactionDTO.builder();

        transactionDTO.id( transaction.getId() );
        transactionDTO.userId( transaction.getUserId() );
        transactionDTO.bookId( transaction.getBookId() );
        transactionDTO.loanDate( transaction.getLoanDate() );
        transactionDTO.returnDate( transaction.getReturnDate() );
        transactionDTO.fineValue( transaction.getFineValue() );

        return transactionDTO.build();
    }
}
