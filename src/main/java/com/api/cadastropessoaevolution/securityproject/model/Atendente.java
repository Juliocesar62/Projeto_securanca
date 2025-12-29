package com.api.cadastropessoaevolution.securityproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "atendente")
@Table(name = "atendente")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "setor")
    private String setor;

    public Atendente(String nome, String email, String setor) {
        this.id = null;
        this.nome = nome;
        this.email = email;
        this.setor = setor;
    }
}
