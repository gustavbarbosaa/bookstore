package br.com.catolica.ms_transaction.repositories;

import br.com.catolica.ms_transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByReturnDateIsNullOrReturnDateBefore(LocalDateTime date);
}
