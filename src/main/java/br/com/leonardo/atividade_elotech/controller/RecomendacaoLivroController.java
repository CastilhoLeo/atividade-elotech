package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.service.RecomendacaoLivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller das recomendações de livros sugeridas ao usuário selecionado
 */

@Controller
@RequestMapping("/recomendacao")
@CrossOrigin(origins = "*")
public class RecomendacaoLivroController {

    @Autowired
    private RecomendacaoLivroService recomendacaoLivroService;

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<LivroDTO>> recomendacaoLivro(@PathVariable Long clienteId){
        return ResponseEntity.ok().body(recomendacaoLivroService.recomendacoesDocliente(clienteId));
    }
}
