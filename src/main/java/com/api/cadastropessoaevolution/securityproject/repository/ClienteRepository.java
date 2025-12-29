package com.api.cadastropessoaevolution.securityproject.repository;

import com.api.cadastropessoaevolution.securityproject.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
