package br.com.catolica.ms_transaction.mapper;

import br.com.catolica.ms_transaction.domain.Transaction;
import br.com.catolica.ms_transaction.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "id", ignore = true)
    Transaction dtoToEntity(TransactionDTO transactionDTO);

    TransactionDTO entityToDTO(Transaction transaction);
}
