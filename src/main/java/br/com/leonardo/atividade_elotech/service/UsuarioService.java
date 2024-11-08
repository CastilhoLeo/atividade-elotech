package br.com.leonardo.atividade_elotech.service;

import br.com.leonardo.atividade_elotech.converter.UsuarioConverter;
import br.com.leonardo.atividade_elotech.dto.UsuarioDTO;
import br.com.leonardo.atividade_elotech.entity.Usuario;
import br.com.leonardo.atividade_elotech.exception.UsuarioNaoEcontradoException;
import br.com.leonardo.atividade_elotech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConverter usuarioConverter;


    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO){

        Usuario usuario = usuarioConverter.toEntity(usuarioDTO);

         return usuarioConverter.toDto(usuarioRepository.save(usuario));
    }


    public UsuarioDTO localizarPeloId(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNaoEcontradoException());

        return usuarioConverter.toDto(usuario);
    }

    public void deletarUsuario(Long id){

        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNaoEcontradoException());

        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setDataCadastro(usuarioDTO.getDataCadastro());

        return usuarioConverter.toDto(usuarioRepository.save(usuario));

    }
}
