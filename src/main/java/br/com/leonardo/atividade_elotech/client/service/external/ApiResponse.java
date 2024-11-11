package br.com.leonardo.atividade_elotech.client.service.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    private List<Item> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private VolumeInfo volumeInfo;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private List<IndustryIdentifier> industryIdentifiers;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndustryIdentifier {
        private String type;
        private String identifier;
    }

}
