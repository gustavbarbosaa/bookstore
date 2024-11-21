package br.com.catolica.ms_user.services;

import br.com.catolica.ms_user.domain.User;
import br.com.catolica.ms_user.dto.UserDTO;
import br.com.catolica.ms_user.mapper.UserMapper;
import br.com.catolica.ms_user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToDTO)
                .toList();
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::entityToDTO);
    }

    public UserDTO save(UserDTO userDTO) {
        Optional.ofNullable(userDTO.getId()).ifPresent(ex -> {
            throw new IllegalArgumentException("O id deve ser nulo");
        });

        User user = userRepository.save(userMapper.dtoToEntity(userDTO));

        return userMapper.entityToDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
