package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.converter.LivroConverter;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.entity.Emprestimo;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.enums.Categoria;
import br.com.leonardo.atividade_elotech.repository.EmprestimoRepository;
import br.com.leonardo.atividade_elotech.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacaoLivroService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroConverter livroConverter;

    public List<LivroDTO> recomendacoesDoUsuario(Long usuarioId){
        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioId(usuarioId);

        List<Categoria> categorias = emprestimos
                .stream()
                .map(e->e.getLivro().getCategoria())
                .distinct()
                .toList();

        return livrosDaCategoria(categorias);

    }

    private List<LivroDTO> livrosDaCategoria(List<Categoria> categorias){

        List<Livro> livros = livroRepository.findByCategoriaIn(categorias);

        return livros.stream().map(e->livroConverter.toDto(e)).toList();
    }


}
