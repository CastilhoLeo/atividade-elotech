package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller das operações de cadastro, exclusão, pesquisa e atualização dos livros
 */

@Controller
@RequestMapping("/livro")
@CrossOrigin(origins = "*")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> pesquisaPeloTitulo (@RequestParam String titulo){
        return ResponseEntity.ok().body(livroService.localizarPeloTitulo(titulo));
    }

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
