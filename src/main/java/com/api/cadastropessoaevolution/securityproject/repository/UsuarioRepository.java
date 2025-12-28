package com.api.cadastropessoaevolution.securityproject.repository;

import com.api.cadastropessoaevolution.securityproject.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario , Long> {

    UserDetails findByLogin(String login);
}



