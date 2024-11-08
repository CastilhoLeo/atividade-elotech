package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroDTO> cadastrarLivro(@RequestBody LivroDTO livroDTO){
        return ResponseEntity.ok().body(livroService.cadastrarLivro(livroDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> localizarPeloId(@PathVariable Long id){
        return ResponseEntity.ok().body(livroService.localizarPeloId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id){
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Long id, @RequestBody LivroDTO livroDTO){
        return ResponseEntity.ok().body(livroService.atualizarLivro(id, livroDTO));
    }
}
