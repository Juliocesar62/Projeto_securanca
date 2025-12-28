package com.api.cadastropessoaevolution.securityproject.dto;

import com.api.cadastropessoaevolution.securityproject.model.enums.RoleUsuario;

public record UsuarioResponseDTO(
        Long id,
        String login,
        String senha,
        String email,
        RoleUsuario role
) {
}
