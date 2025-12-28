package com.api.cadastropessoaevolution.securityproject.model.enums;

import lombok.Getter;

@Getter
public enum RoleUsuario {

    ADMIN("admin"),
    USER("user");

    private final String role;

    RoleUsuario (String role){
        this.role = role;
    }

}
