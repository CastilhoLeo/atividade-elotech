//package br.com.leonardo.atividade_elotech.client.converter;
//
//import br.com.leonardo.atividade_elotech.client.apiResponse.ApiResponse;
//import br.com.leonardo.atividade_elotech.client.apiResponse.Items;
//import br.com.leonardo.atividade_elotech.dto.LivroDTO;
//import org.springframework.stereotype.Service;
//
///**
// * Classe criada para conversão dos itens recebidos da Api Google Books para LivroDTO
// */
//@Service
//public class LivroApiConverter {
//
//
//    public LivroDTO toLivroDto(Items item){
//
//        LivroDTO livroDTO = new LivroDTO();
//
//        livroDTO.setTitulo(item.getVolumeInfo().getTitle());
//
//        if(item.getVolumeInfo().getAuthors() == null){
//            livroDTO.setAutor(null);
//        }else{
//            livroDTO.setAutor(item.getVolumeInfo().getAuthors().get(0));
//        }
//
//        if(item.getVolumeInfo()
//                .getIndustryIdentifiers()
//                .stream()
//                .filter(e -> e.getType().equals("ISBN_13"))
//                .toList().isEmpty()){
//
//            livroDTO.setIsbn(null);
//
//        } else{
//
//            livroDTO.setIsbn(item.getVolumeInfo()
//                    .getIndustryIdentifiers()
//                    .stream()
//                    .filter(e->e.getType().equals("ISBN_13"))
//                    .toList()
//                    .get(0)
//                    .getIdentifier());
//        }
//
//        return livroDTO;
//    }
//}
