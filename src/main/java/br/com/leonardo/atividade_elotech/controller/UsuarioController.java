package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.dto.UsuarioDTO;
import br.com.leonardo.atividade_elotech.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller das operações de cadastro, exclusão, pesquisa e atualização dos usuários
 */

@Controller
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok().body(usuarioService.cadastrarUsuario(usuarioDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> localizarPeloId(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.localizarPeloId(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> localizarNome(@RequestParam String nome){
        return ResponseEntity.ok().body(usuarioService.localizarNome(nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok().body(usuarioService.atualizarUsuario(id, usuarioDTO));
    }

}
