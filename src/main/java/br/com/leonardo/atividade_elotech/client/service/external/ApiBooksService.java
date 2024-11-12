package br.com.leonardo.atividade_elotech.client.service.external;

import br.com.leonardo.atividade_elotech.client.apiResponse.Items;
import br.com.leonardo.atividade_elotech.client.converter.LivroApiConverter;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Classe que envia o request para a Api GoogleBooks e converte o resultado recebido em um list de LivroDTO
 */
@Component
public class ApiBooksService {

    @Autowired
    private LivroApiConverter livroApiConverter;

    @Autowired
    private WebClient webClient;


    @Value("${API_KEY}") // Inserir nas variaveis de ambiente
    private String apiKey;

    private String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:"; //URL para pesquisa com base no titulo

    /**
     * Método que envia o request para a Api e retorna um list com os livros no formato do response recebido
     * @param titulo
     * @return
     */
    private Items apiResponse(String titulo){

        String urlCompleta = String.format("%s%s&hl=pt-BR&%s", url, titulo, apiKey); // URL completa com idioma pt-BR

        Items listaLivros =  webClient
                .get()
                .uri(urlCompleta)
                .retrieve()
                .bodyToMono(Items.class).block();

        return listaLivros;
    }

    /**
     * Método que efetiva o envio do request e converte os dados recebidos em um list de LivroDTO
     * @param titulo
     * @return
     */
    public List<LivroDTO> buscaLivrosApi(String titulo){

        Items apiResponse = apiResponse(titulo);

        return apiResponse.getItems().stream().map(e->livroApiConverter.toLivroDto(e)).toList();
    }



}
