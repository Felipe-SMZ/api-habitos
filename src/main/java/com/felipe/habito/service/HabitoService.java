package com.felipe.habito.service;

import com.felipe.habito.model.Habito;
import com.felipe.habito.repository.HabitoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HabitoService {

    private final HabitoRepository habitoRepository;

    public HabitoService(HabitoRepository habitoRepository) {
        this.habitoRepository = habitoRepository;
    }

    private Habito buscarOuFalhar(Long id) {
        return habitoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Habito " + id + " não encontrado"
                ));
    }

    public Habito salvar(Habito habito) {
        //validacao
        if (habito.getNome() == null || habito.getNome().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome é obrigatório"
            );
        }
        if (habito.getFrequencia() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Frequência é obrigatória"
            );
        }
        return habitoRepository.save(habito);
    }

    public List<Habito> buscarTodos() {
        return habitoRepository.findAll();
    }

    public Habito buscarPorId(Long id) {
        return buscarOuFalhar(id);
    }

    public Habito atualizar(Long id, Habito habitoAtualizado) {

        Habito habito = buscarOuFalhar(id);

        if (habitoAtualizado.getNome() == null || habitoAtualizado.getNome().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome é obrigatório"
            );
        }

        if (habitoAtualizado.getFrequencia() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Frequência é obrigatória"
            );
        }

        if (habito.getFrequencia() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Frequência é obrigatória"
            );
        }

        // 🔥 Atualiza apenas os campos necessários
        habito.setNome(habitoAtualizado.getNome());
        habito.setDescricao(habitoAtualizado.getDescricao());
        habito.setFrequencia(habitoAtualizado.getFrequencia());

        return habitoRepository.save(habito);
    }

    public void deletar(Long id) {
        Habito habito = buscarOuFalhar(id);
        habitoRepository.deleteById(id);
    }
}
