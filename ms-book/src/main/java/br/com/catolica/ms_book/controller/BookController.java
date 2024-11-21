package br.com.catolica.ms_book.controller;

import br.com.catolica.ms_book.dto.BookDTO;
import br.com.catolica.ms_book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAll());
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Optional<BookDTO>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}