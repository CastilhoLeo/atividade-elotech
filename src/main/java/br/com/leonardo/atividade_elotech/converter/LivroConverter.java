package br.com.leonardo.atividade_elotech.converter;

import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import br.com.leonardo.atividade_elotech.entity.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Realiza a conversão entre as classes Livro e LivroDTO
 */

@Component
public class LivroConverter {

    @Autowired
    private ModelMapper modelMapper;

    public LivroDTO toDto(Livro livro){

        return modelMapper.map(livro, LivroDTO.class);
    }

    public Livro toEntity(LivroDTO livroDTO){
        return modelMapper.map(livroDTO, Livro.class);
    }
}
