package br.com.catolica.ms_transaction.client;

import br.com.catolica.ms_transaction.dto.response.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "${feign.users.name}", url = "${feign.users.url}")
public interface UserTransaction {
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id);
}
