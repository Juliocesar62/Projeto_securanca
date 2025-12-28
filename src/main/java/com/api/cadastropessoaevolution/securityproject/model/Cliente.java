package com.api.cadastropessoaevolution.securityproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "cliente")
@Table(name = "cliente")
@AllArgsConstructor
@Getter@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    public Cliente(String nome, String email, String telefone) {
        this.id = null;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    
}
