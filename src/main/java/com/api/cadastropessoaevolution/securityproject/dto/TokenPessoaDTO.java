package com.api.cadastropessoaevolution.securityproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@NoArgsConstructor
public class TokenPessoaDTO {
    private Long idPessoa;

    public TokenPessoaDTO(Long id) {
        this.idPessoa = id;
    }
}
