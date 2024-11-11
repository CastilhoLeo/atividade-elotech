package br.com.leonardo.atividade_elotech.client.service.external;

import br.com.leonardo.atividade_elotech.client.converter.LivroApiConverter;
import br.com.leonardo.atividade_elotech.dto.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ApiBooksService {

    @Autowired
    private LivroApiConverter livroApiConverter;


    private final WebClient webClient;

    public ApiBooksService (WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }


    @Value("${API_KEY}")
    private String apiKey;

    private String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:";

    private ApiResponse apiResponse(String titulo){

        String urlCompleta = String.format("%s%s&hl=pt-BR&%s", url, titulo, apiKey);

        ApiResponse listaLivros =  webClient.get()
                .uri(urlCompleta)
                .retrieve()
                .bodyToMono(ApiResponse.class).block();

        return listaLivros;
    }

    public List<LivroDTO> buscaLivrosApi(String titulo){

        ApiResponse apiResponse = apiResponse(titulo);

        return apiResponse.getItems().stream().map(e->livroApiConverter.toLivroDto(e)).toList();
    }



}
