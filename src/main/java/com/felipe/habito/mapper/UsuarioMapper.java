package com.felipe.habito.mapper;

import com.felipe.habito.dto.request.UsuarioRequestDTO;
import com.felipe.habito.dto.response.UsuarioResponseDTO;
import com.felipe.habito.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioResponseDTO toDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    public Usuario toEntity(UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(usuarioDTO.senha());
        return usuario;
    }
}
