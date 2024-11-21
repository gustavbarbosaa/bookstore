package br.com.catolica.ms_user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {
    private String number;

    @Column(nullable = false)
    @NotNull(message = "O campo road é obrigatório!")
    private String road;

    @Column(nullable = false)
    @NotNull(message = "O campo neighborhood é obrigatório!")
    private String neighborhood;

    @Column(nullable = false)
    @NotNull(message = "O campo city é obrigatório!")
    private String city;

    @Column(nullable = false)
    @NotNull(message = "O campo postalCode é obrigatório!")
    private String postalCode;

    @Column(nullable = false)
    @NotNull(message = "O campo state é obrigatório!")
    private String state;
}
