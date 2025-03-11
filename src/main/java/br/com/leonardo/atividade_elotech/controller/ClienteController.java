package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.dto.ClienteDTO;
import br.com.leonardo.atividade_elotech.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller das operações de cadastro, exclusão, pesquisa e atualização dos usuários
 */

@Controller
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarcliente(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok().body(clienteService.cadastrarcliente(clienteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> localizarPeloId(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.localizarPeloId(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> localizarNome(@RequestParam String nome){
        return ResponseEntity.ok().body(clienteService.localizarNome(nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarcliente(@PathVariable Long id){
        clienteService.deletarcliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarcliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok().body(clienteService.atualizarcliente(id, clienteDTO));
    }

}
