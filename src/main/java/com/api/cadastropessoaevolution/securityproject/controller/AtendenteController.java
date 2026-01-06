package com.api.cadastropessoaevolution.securityproject.controller;

import com.api.cadastropessoaevolution.securityproject.dto.CadastroClienteDTO;
import com.api.cadastropessoaevolution.securityproject.service.AtendenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/atendente")
@RequiredArgsConstructor
public class AtendenteController {

    private final AtendenteService service;

    @PostMapping("/cadastrarCliente")
    public ResponseEntity<String> cadastrarCliente(@RequestBody CadastroClienteDTO cadastroClienteDTO){
        return service.cadastrarCliente(cadastroClienteDTO);
    }


}
