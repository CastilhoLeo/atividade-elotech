package br.com.leonardo.atividade_elotech.client.apiResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {

    private String title;

    private List<String> authors;

    private List<IndustryIdentifier> industryIdentifiers;

}
