package com.api.cadastropessoaevolution.securityproject.service;

import com.api.cadastropessoaevolution.securityproject.dto.CadastroUsuarioDTO;
import com.api.cadastropessoaevolution.securityproject.dto.LoginDTO;
import com.api.cadastropessoaevolution.securityproject.model.Atendente;
import com.api.cadastropessoaevolution.securityproject.model.Cliente;
import com.api.cadastropessoaevolution.securityproject.model.Usuario;
import com.api.cadastropessoaevolution.securityproject.model.enums.RoleUsuario;
import com.api.cadastropessoaevolution.securityproject.repository.AtendenteRepository;
import com.api.cadastropessoaevolution.securityproject.repository.ClienteRepository;
import com.api.cadastropessoaevolution.securityproject.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final AtendenteRepository atendenteRepository;

    private final ClienteRepository clienteRepository;

    private final TokenService tokenService;


    public ResponseEntity<String> cadastroUsuario(CadastroUsuarioDTO cadastroUsuarioDTO) {
        Usuario entidade;
        if (cadastroUsuarioDTO.role() == RoleUsuario.ADMIN) {
            CadastroUsuarioDTO usuarioRequestDTO = new CadastroUsuarioDTO(cadastroUsuarioDTO.login() , cadastroUsuarioDTO.senha(), cadastroUsuarioDTO.email(), cadastroUsuarioDTO.setor(), cadastroUsuarioDTO.role());
            Atendente atendente = new Atendente(usuarioRequestDTO.login() , usuarioRequestDTO.senha() , usuarioRequestDTO.setor());
            entidade = new Usuario(cadastroUsuarioDTO.login(), passwordEncoder.encode(cadastroUsuarioDTO.senha()), RoleUsuario.ADMIN, cadastroUsuarioDTO.email(), atendente);
            atendenteRepository.save(atendente);
            usuarioRepository.save(entidade);
            return ResponseEntity.ok("ATENDENTE salvo com sucesso");
        } else {
            Cliente cliente = clienteRepository.encontrarCliente(cadastroUsuarioDTO.login() , cadastroUsuarioDTO.email());
            entidade = new Usuario(cadastroUsuarioDTO.login(), passwordEncoder.encode(cadastroUsuarioDTO.senha()), RoleUsuario.USER, cadastroUsuarioDTO.email(), cliente);
            usuarioRepository.save(entidade);
            return ResponseEntity.ok("CLIENTE cadastrado");
        }
    }

    public ResponseEntity<String> login(LoginDTO data) {
        if (usuarioRepository.findByLogin(data.login()) != null) {
            try {
                var loginSenha = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
                var autenticar = authenticationManager.authenticate(loginSenha);
                var token = tokenService.generationToken((Usuario) autenticar.getPrincipal());
                return ResponseEntity.ok(token);
            } catch (BadCredentialsException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senhas incorretos");
            }
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senhas incorretos");
    }
}

