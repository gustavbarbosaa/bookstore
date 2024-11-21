package br.com.catolica.ms_book.service;

import br.com.catolica.ms_book.domain.Book;
import br.com.catolica.ms_book.dto.BookDTO;
import br.com.catolica.ms_book.mapper.BookMapper;
import br.com.catolica.ms_book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDTO save(@RequestBody BookDTO bookDTO) {
        Optional.ofNullable(bookDTO.getId()).ifPresent(ex -> {
            throw new IllegalArgumentException("O id deve ser nulo!");
        });

        Book book = bookRepository.save(bookMapper.dtoBookToModel(bookDTO));

        return bookMapper.entityToDTO(book);
    }

    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id).map(bookMapper::entityToDTO);
    }

    public List<BookDTO> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::entityToDTO)
                .toList();
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}