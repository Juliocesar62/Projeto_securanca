package com.api.cadastropessoaevolution.securityproject.dto;

import com.api.cadastropessoaevolution.securityproject.model.enums.RoleUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioResponseDTO(
        @NotNull String login,
        @NotNull String senha,
        @Email String email,
        @NotNull RoleUsuario role
) {
}
