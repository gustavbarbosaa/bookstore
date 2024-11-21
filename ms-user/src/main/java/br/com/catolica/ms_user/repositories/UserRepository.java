package br.com.catolica.ms_user.repositories;

import br.com.catolica.ms_user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
