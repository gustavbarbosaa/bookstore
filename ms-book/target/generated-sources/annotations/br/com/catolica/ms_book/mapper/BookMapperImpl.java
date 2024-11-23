package br.com.catolica.ms_book.mapper;

import br.com.catolica.ms_book.domain.Book;
import br.com.catolica.ms_book.dto.BookDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-23T08:37:16-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book dtoBookToModel(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.titulo( bookDTO.getTitulo() );
        book.escritor( bookDTO.getEscritor() );
        book.dataPublicacao( bookDTO.getDataPublicacao() );
        book.genero( bookDTO.getGenero() );
        book.editora( bookDTO.getEditora() );
        book.edicao( bookDTO.getEdicao() );
        book.categoria( bookDTO.getCategoria() );
        book.isbn( bookDTO.getIsbn() );

        return book.build();
    }

    @Override
    public BookDTO entityToDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO.BookDTOBuilder bookDTO = BookDTO.builder();

        bookDTO.id( book.getId() );
        bookDTO.titulo( book.getTitulo() );
        bookDTO.escritor( book.getEscritor() );
        bookDTO.dataPublicacao( book.getDataPublicacao() );
        bookDTO.genero( book.getGenero() );
        bookDTO.editora( book.getEditora() );
        bookDTO.edicao( book.getEdicao() );
        bookDTO.categoria( book.getCategoria() );
        bookDTO.isbn( book.getIsbn() );

        return bookDTO.build();
    }
}
