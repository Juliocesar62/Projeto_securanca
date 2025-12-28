package com.api.cadastropessoaevolution.securityproject.model;

import com.api.cadastropessoaevolution.securityproject.model.enums.RoleUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
@Table(name = "usuario")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login" , nullable = false)
    private String login;

    @Column(name = "senha" , nullable = false)
    private String senha;

    @Column(name = "role")
    private RoleUsuario role;

    @Column(name = "email" , nullable = false)
    private String email;

    public Usuario(String login, String senha, RoleUsuario role, String email) {
        this.id = null;
        this.login = login;
        this.senha = senha;
        this.role = role;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == RoleUsuario.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
