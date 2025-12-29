package com.api.cadastropessoaevolution.securityproject.dto;

import com.api.cadastropessoaevolution.securityproject.model.enums.RoleUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioRequestDTO(
        @NotBlank String login,
        @NotBlank String senha,
        @Email String email,
        @NotBlank String setor,
        @NotNull RoleUsuario role

) {
}
