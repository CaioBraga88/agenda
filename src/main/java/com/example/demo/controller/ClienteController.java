package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(origins = "http://localhost:5501")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/buscar")
    public List<Cliente> buscar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf) {
        if (nome != null && cpf != null) {
            return clienteRepository.findByNomeContainingIgnoreCaseAndCpf(nome, cpf);
        } else if (nome != null) {
            return clienteRepository.findByNomeContainingIgnoreCase(nome);
        } else if (cpf != null) {
            return clienteRepository.findByCpf(cpf)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        } else {
            return clienteRepository.findAll();
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Cliente cliente) {
        Optional<Cliente> existente = clienteRepository.findByCpf(cliente.getCpf());
        if (existente.isPresent()) {
            Map<String, String> erro = new HashMap<>();
            erro.put("cpf", "CPF já cadastrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }

        Cliente salvo = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody @Valid Cliente dadosAtualizados) {
        Optional<Cliente> existente = clienteRepository.findByCpf(dadosAtualizados.getCpf());
        if (existente.isPresent() && !existente.get().getById().equals(id)) {
            Map<String, String> erro = new HashMap<>();
            erro.put("cpf", "CPF já cadastrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNome(dadosAtualizados.getNome());
            cliente.setCpf(dadosAtualizados.getCpf());
            cliente.setDataNascimento(dadosAtualizados.getDataNascimento());
            cliente.setEndereco(dadosAtualizados.getEndereco());
            return ResponseEntity.ok(clienteRepository.save(cliente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado com ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
