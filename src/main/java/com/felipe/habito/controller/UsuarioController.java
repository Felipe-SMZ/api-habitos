package com.felipe.habito.controller;

import com.felipe.habito.dto.request.UsuarioRequestDTO;
import com.felipe.habito.dto.response.UsuarioResponseDTO;
import com.felipe.habito.mapper.UsuarioMapper;
import com.felipe.habito.model.Usuario;
import com.felipe.habito.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    //POST
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuarioResponseEntity(@RequestBody @Valid UsuarioRequestDTO usuarioDTO) {
        Usuario usuarioEntity = usuarioMapper.toEntity(usuarioDTO);
        Usuario salvo = usuarioService.salvar(usuarioEntity);
        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.toDTO(salvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioRequestDTO usuarioDTO
    ) {
        Usuario usuarioEntity = usuarioMapper.toEntity(usuarioDTO);
        Usuario atualizado = usuarioService.atualizar(id, usuarioEntity);
        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.toDTO(atualizado);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioResponseDTO);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
