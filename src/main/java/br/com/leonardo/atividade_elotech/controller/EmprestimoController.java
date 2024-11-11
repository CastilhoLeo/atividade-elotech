package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.dto.EmprestimoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestDevolucaoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestEmprestimoDTO;
import br.com.leonardo.atividade_elotech.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller das operações de cadastro, devolução e pesquisa dos empréstimos realizados
 */

@Controller
@RequestMapping("/emprestimo")
@CrossOrigin(origins = "*")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;


    @GetMapping
    public ResponseEntity<Page<EmprestimoDTO>> pesquisarEmprestimo(Pageable pageable){

        return ResponseEntity.ok().body(emprestimoService.pesquisarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<EmprestimoDTO> cadastrarEmprestimo(@RequestBody RequestEmprestimoDTO request){

        return ResponseEntity.ok().body(emprestimoService.cadastrarEmprestimo(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> devolverEmprestimo(@PathVariable Long id, @RequestBody RequestDevolucaoDTO request) {
        return ResponseEntity.ok().body(emprestimoService.devolverEmprestimo(id, request));
    }
}
