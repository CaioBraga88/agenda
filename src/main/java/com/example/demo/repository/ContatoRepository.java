package com.example.demo.repository;

import com.example.demo.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    void deleteAllByClienteId(Long clienteId);

    List<Contato> findAllByClienteId(Long clienteId);
}
