package br.com.leonardo.atividade_elotech.client.converter;

import br.com.leonardo.atividade_elotech.client.apiResponse.Item;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import org.springframework.stereotype.Component;

/**
 * Classe criada para conversão dos itens recebidos da Api Google Books paraLivroDTO
 * (Não convertendo a dataPuvlicacao e categoria)
 */
@Component
public class LivroApiConverter {

    public LivroDTO toLivroDto(Item item){

        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo(item.getVolumeInfo().getTitle());


        // Tratamento para os casos não possuem autores

        if(item.getVolumeInfo().getAuthors() == null){

            livroDTO.setAutor(null);

        }else{

            livroDTO.setAutor(item.getVolumeInfo().getAuthors().get(0));
        }

        //Tratamento para selecionar somente o ISBN dos que são formato ISBN_13

        if(item.getVolumeInfo()
                .getIndustryIdentifiers()
                .stream()
                .filter(e -> e.getType().equals("ISBN_13"))
                .toList().isEmpty()){

            livroDTO.setIsbn(null);

        } else{

            livroDTO.setIsbn(item.getVolumeInfo()
                    .getIndustryIdentifiers()
                    .stream()
                    .filter(e->e.getType().equals("ISBN_13"))
                    .toList()
                    .get(0)
                    .getIdentifier());
        }

        return livroDTO;
    }

}
