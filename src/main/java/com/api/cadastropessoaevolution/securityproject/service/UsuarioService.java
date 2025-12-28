package com.api.cadastropessoaevolution.securityproject.service;

import com.api.cadastropessoaevolution.securityproject.dto.CadastroUsuarioResponseDTO;
import com.api.cadastropessoaevolution.securityproject.dto.UsuarioResponseDTO;
import com.api.cadastropessoaevolution.securityproject.model.Usuario;
import com.api.cadastropessoaevolution.securityproject.model.enums.RoleUsuario;
import com.api.cadastropessoaevolution.securityproject.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UsuarioResponseDTO> cadastroUsuario(CadastroUsuarioResponseDTO cadastroUsuarioResponseDTO) {
        Usuario entidade;
        if (cadastroUsuarioResponseDTO.role() == RoleUsuario.ADMIN) {
            entidade = new Usuario(cadastroUsuarioResponseDTO.login(), passwordEncoder.encode(cadastroUsuarioResponseDTO.senha()), RoleUsuario.ADMIN, cadastroUsuarioResponseDTO.email());
            usuarioRepository.save(entidade);
        }
        else
            entidade = new Usuario(cadastroUsuarioResponseDTO.login() , passwordEncoder.encode(cadastroUsuarioResponseDTO.senha()) , RoleUsuario.USER , cadastroUsuarioResponseDTO.email());
        return ResponseEntity.ok().body(new UsuarioResponseDTO(entidade.getId() , entidade.getLogin() , entidade.getSenha() , entidade.getEmail() , entidade.getRole()));
    }
}
