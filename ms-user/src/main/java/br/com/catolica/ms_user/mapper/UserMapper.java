package br.com.catolica.ms_user.mapper;

import br.com.catolica.ms_user.domain.User;
import br.com.catolica.ms_user.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User dtoToEntity(UserDTO userDTO);

    UserDTO entityToDTO(User user);
}
