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
        List<Emprestimo> emprestimosDoUsuario = emprestimoRepository.findByUsuarioId(usuarioId);

        List<Livro> livrosDoUsuario = emprestimosDoUsuario
                .stream()
                .map(e->e.getLivro()).toList();

        List<Categoria> categorias = livrosDoUsuario.stream().map(e->e.getCategoria()).distinct()
                .toList();

        List<Livro> livrosRecomendados = livrosDaCategoria(categorias);

        List<Livro> livrosRecomendadosFiltrado = retiraLivrosJaEmprestados(livrosDoUsuario, livrosRecomendados);

        return livrosRecomendadosFiltrado.stream().map(l->livroConverter.toDto(l)).toList();
    }

    private List<Livro> livrosDaCategoria(List<Categoria> categorias){

        List<Livro> livros = livroRepository.findByCategoriaIn(categorias);

        return livros;
    }

    private List<Livro> retiraLivrosJaEmprestados(List<Livro> livrosDoUsuario, List<Livro> livrosRecomendados){

        livrosRecomendados.removeAll(livrosDoUsuario);

        return livrosRecomendados;
    }


}
