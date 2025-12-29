package com.api.cadastropessoaevolution.securityproject.repository;

import com.api.cadastropessoaevolution.securityproject.model.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente , Long> {
}
