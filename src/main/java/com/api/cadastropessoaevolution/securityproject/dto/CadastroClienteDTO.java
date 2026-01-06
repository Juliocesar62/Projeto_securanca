package com.api.cadastropessoaevolution.securityproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastroClienteDTO(
        @NotBlank String nome,
        @Email String email,
        String telefone
) {
}
