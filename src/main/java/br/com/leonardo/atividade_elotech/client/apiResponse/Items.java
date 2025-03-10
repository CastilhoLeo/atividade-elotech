package br.com.leonardo.atividade_elotech.client.apiResponse;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Classe que representa os campos selecionados para receber da Api GoogleBooks
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    private List<Item> items;

}
