package br.com.catolica.ms_book.repository;

import br.com.catolica.ms_book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
