package com.api.cadastropessoaevolution.securityproject.repository;

import com.api.cadastropessoaevolution.securityproject.model.Cliente;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeAndEmailAndTelefone(String email, String nome, String telefone);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM cliente c WHERE c.telefone = :telefone)", nativeQuery = true)
    boolean existeByTelefone(@Param("telefone") String telefone);

    @Query(value = "SELECT * FROM cliente WHERE (nome ILIKE CONCAT('%' , :nome , '%') AND email ILiKE CONCAT ('%' ,  :email , '%'))" , nativeQuery = true)
    Cliente encontrarCliente( @Param("nome") String login, @Param("email") String email);

    boolean existsByEmail(@Email String email);
}
