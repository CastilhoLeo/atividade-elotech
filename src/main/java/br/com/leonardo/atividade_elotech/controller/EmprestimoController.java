package br.com.leonardo.atividade_elotech.controller;

import br.com.leonardo.atividade_elotech.dto.EmprestimoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestDevolucaoDTO;
import br.com.leonardo.atividade_elotech.dto.RequestEmprestimoDTO;
import br.com.leonardo.atividade_elotech.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/emprestimo")
@CrossOrigin(origins = "*")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<EmprestimoDTO> cadastrarEmprestimo(@RequestBody RequestEmprestimoDTO request){
        return ResponseEntity.ok().body(emprestimoService.cadastrarEmprestimo(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> devolverEmprestimo(@PathVariable Long id, @RequestBody RequestDevolucaoDTO requestDevolucaoDTO) {
        System.out.println("EmprestimoDTO recebido: " + requestDevolucaoDTO);
        return ResponseEntity.ok().body(emprestimoService.devolverEmprestimo(id, requestDevolucaoDTO));
    }
}
