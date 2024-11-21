package br.com.catolica.ms_transaction.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotNull(message = "O campo nome é obrigatório!")
    private String name;
    @Email(message = "Email inválio")
    @NotNull(message = "O campo email é obrigatório!")
    private String email;
    @NotNull(message = "O campo email é obrigatório!")
    private String phone;
    @NotNull(message = "O campo CPF é obrigatório!")
    @CPF(message = "CPF inválido!")
    private String cpf;
    private AddressDTO address;
    @NotNull(message = "O campo tipo de usuário é obrigatório!")
    private String typeUser;
}