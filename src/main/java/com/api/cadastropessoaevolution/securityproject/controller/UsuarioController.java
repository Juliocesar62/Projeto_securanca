package com.api.cadastropessoaevolution.securityproject.controller;

import com.api.cadastropessoaevolution.securityproject.dto.CadastroUsuarioResponseDTO;
import com.api.cadastropessoaevolution.securityproject.dto.UsuarioResponseDTO;
import com.api.cadastropessoaevolution.securityproject.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody CadastroUsuarioResponseDTO cadastroUsuarioResponseDTO){
        return ResponseEntity.ok(service.cadastroUsuario(cadastroUsuarioResponseDTO).getBody());
    }

}