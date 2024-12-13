package br.com.catolica.ms_book.mapper;

import br.com.catolica.ms_book.domain.Book;
import br.com.catolica.ms_book.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book dtoBookToModel(BookDTO bookDTO);

    BookDTO entityToDTO(Book book);
}
