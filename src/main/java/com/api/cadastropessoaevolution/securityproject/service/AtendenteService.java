package com.api.cadastropessoaevolution.securityproject.service;

import com.api.cadastropessoaevolution.securityproject.dto.CadastroClienteDTO;
import com.api.cadastropessoaevolution.securityproject.dto.CadastroUsuarioRequestDTO;
import com.api.cadastropessoaevolution.securityproject.model.Cliente;
import com.api.cadastropessoaevolution.securityproject.model.enums.RoleUsuario;
import com.api.cadastropessoaevolution.securityproject.repository.AtendenteRepository;
import com.api.cadastropessoaevolution.securityproject.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendenteService {

    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

    private final AtendenteRepository atendenteRepository;

    private final UsuarioService usuarioService;

    private final ClienteRepository clienteRepository;

    public ResponseEntity<String> cadastrarCliente(CadastroClienteDTO cadastroClienteDTO) {
        var validoCliente = verificarCliente(cadastroClienteDTO);

        if (validoCliente.getStatusCode().equals(HttpStatus.OK)) {
            Cliente cliente = new Cliente(cadastroClienteDTO.nome() , cadastroClienteDTO.email() , cadastroClienteDTO.telefone());
            SecureRandom random = new SecureRandom();
            StringBuilder sb = new StringBuilder(10);
            for (int i = 0; i < 10; i++) {
                int indiceAleatorio = random.nextInt(CARACTERES.length());
                sb.append(CARACTERES.charAt(indiceAleatorio));
            }
            CadastroUsuarioRequestDTO clienteUsuario = new CadastroUsuarioRequestDTO(cliente.getNome(), sb.toString(), cliente.getEmail(), "Cliente", RoleUsuario.USER);
            clienteRepository.save(cliente);
            usuarioService.cadastroUsuario(clienteUsuario);
            return validoCliente;
        }
        return validoCliente;
    }

    public ResponseEntity<String> verificarCliente(CadastroClienteDTO cadastroClienteDTO) {
        List<Cliente> clientesList = clienteRepository.findByNomeAndEmailAndTelefone(cadastroClienteDTO.nome(), cadastroClienteDTO.email(), cadastroClienteDTO.telefone());

        if (clientesList.isEmpty()) {
            if (cadastroClienteDTO.nome().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome não pode ser vazio.");
            }
            if (!cadastroClienteDTO.nome().matches("^[A-Za-zÀ-ú ]+$")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome não pode ter caracteres especiais ou números.");
            }
            if (clienteRepository.existeByTelefone(cadastroClienteDTO.telefone())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Telefone já cadastrado.");
            }
            if (cadastroClienteDTO.email().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O email não pode ser vazia.");
            }
            if (clienteRepository.existsByEmail(cadastroClienteDTO.email())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
            }
            return ResponseEntity.ok().body("Cliente liberado para cadastro!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente já cadastrado.");
        }
    }
}
