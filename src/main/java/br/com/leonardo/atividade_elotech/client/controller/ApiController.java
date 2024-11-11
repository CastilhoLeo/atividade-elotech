package br.com.leonardo.atividade_elotech.client.controller;

import br.com.leonardo.atividade_elotech.client.service.ApiService;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/apiBooks")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> buscaApiBooks(@RequestParam String titulo){

        return ResponseEntity.ok().body(apiService.buscaApiBooks(titulo));
    }
}
