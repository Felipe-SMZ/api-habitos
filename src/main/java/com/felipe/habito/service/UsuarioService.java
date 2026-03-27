package com.felipe.habito.service;

import com.felipe.habito.model.Usuario;
import com.felipe.habito.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private Usuario buscarOuFalhar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario " + id + " não encontrado"));
    }

    private void validarEmail(Long id, Usuario usuario) {
        usuarioRepository.findByEmail(usuario.getEmail())
                .ifPresent(usuarioExistente -> {
                    // Objects.equals compara dois valores e retorna true se ambos forem iguais
                    // ou false se um deles for nulo, sem estourar erro.
                    if (!Objects.equals(usuarioExistente.getId(), id)) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT, "E-mail já cadastrado");
                    }
                });
    }

    public Usuario salvar(Usuario usuario) {
        validarEmail(null, usuario);
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return buscarOuFalhar(id);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarOuFalhar(id);
        validarEmail(id, usuarioAtualizado);
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        Usuario usuario = buscarOuFalhar(id);
        usuarioRepository.delete(usuario);
    }
}