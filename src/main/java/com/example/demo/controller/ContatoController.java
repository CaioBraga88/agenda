package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.model.Contato;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ContatoRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5501")
@RestController
@RequestMapping("/clientes/{clienteId}/contatos")
public class ContatoController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public List<Contato> listar(@PathVariable Long clienteId) {
        return contatoRepository.findAll()
                .stream()
                .filter(c -> c.getCliente().getById().equals(clienteId))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Contato cadastrar(@PathVariable Long clienteId, @RequestBody  @Valid Contato contato) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        contato.setCliente(cliente);
        return contatoRepository.save(contato);
    }

    @PutMapping("/{contatoId}")
    public Contato editar(@PathVariable Long clienteId, @PathVariable Long contatoId,
            @RequestBody  @Valid Contato dadosAtualizados) {
        Contato contato = contatoRepository.findById(contatoId)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));

        if (!contato.getCliente().getById().equals(clienteId)) {
            throw new RuntimeException("Este contato não pertence ao cliente informado.");
        }

        contato.setTipo(dadosAtualizados.getTipo());
        contato.setValor(dadosAtualizados.getValor());
        contato.setObservacao(dadosAtualizados.getObservacao());

        return contatoRepository.save(contato);
    }

    @DeleteMapping("/{contatoId}")
    public void deletar(@PathVariable Long clienteId, @PathVariable Long contatoId) {
        Contato contato = contatoRepository.findById(contatoId)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));

        if (!contato.getCliente().getById().equals(clienteId)) {
            throw new RuntimeException("Este contato não pertence ao cliente informado.");
        }

        contatoRepository.deleteById(contatoId);
    }
}
