package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.converter.LivroConverter;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.entity.Livro;
import br.com.leonardo.atividade_elotech.exception.LivroNaoEncontradoException;
import br.com.leonardo.atividade_elotech.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroConverter livroConverter;

    public List<LivroDTO> localizarPeloTitulo(String titulo){

        List<Livro> livros = livroRepository.findByTituloContaining(titulo);

        return livros.stream()
                .map(livro->livroConverter.toDto(livro))
                .toList();

    }


    public LivroDTO cadastrarLivro(LivroDTO livroDTO){

        Livro livro = livroConverter.toEntity(livroDTO);

        return livroConverter.toDto(livroRepository.save(livro));
    }


    public LivroDTO localizarPeloId(Long id){

        Livro livro = livroRepository.findById(id)
                .orElseThrow(()-> new LivroNaoEncontradoException());

        return livroConverter.toDto(livro);
    }

    public void deletarLivro(Long id){

        livroRepository.deleteById(id);
    }


    public LivroDTO atualizarLivro(Long id, LivroDTO livroDTO){

        Livro livro = livroRepository.findById(id)
                .orElseThrow(()-> new LivroNaoEncontradoException());


        livro.setAutor(livroDTO.getAutor());
        livro.setIsbn(livroDTO.getIsbn());
        livro.setCategoria(livroDTO.getCategoria());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setDataPublicacao(livroDTO.getDataPublicacao());

        return livroConverter.toDto(livroRepository.save(livro));

    }
}
