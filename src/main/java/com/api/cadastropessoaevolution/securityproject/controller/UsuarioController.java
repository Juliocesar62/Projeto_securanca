package com.api.cadastropessoaevolution.securityproject.controller;

import com.api.cadastropessoaevolution.securityproject.dto.*;
import com.api.cadastropessoaevolution.securityproject.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody CadastroUsuarioRequestDTO cadastroUsuarioRequestDTO){
        return ResponseEntity.ok(service.cadastroUsuario(cadastroUsuarioRequestDTO).getBody());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO data) {
        return ResponseEntity.ok(service.login(data).getBody());
    }

}