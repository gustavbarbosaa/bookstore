package br.com.catolica.ms_user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "O campo name é obrigatório!")
    private String name;

    @Column(nullable = false, unique = true)
    @Email(message = "Insira um email válido!")
    @NotNull(message = "O campo email é obrigatório!")
    private String email;

    @Column(nullable = false, unique = true)
    @NotNull(message = "O campo phone é obrigatório!")
    private String phone;

    @Column(nullable = false, unique = true)
    @NotNull(message = "O campo CPF é obrigatório!")
    @CPF(message = "Insira um CPF válido!")
    private String cpf;

    @Embedded
    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    private String typeUser;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
