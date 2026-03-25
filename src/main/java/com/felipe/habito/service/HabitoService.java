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

        String freq = habito.getFrequencia().toUpperCase();

        if (!freq.equals("DIARIO") && !freq.equals("SEMANAL")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Frequência deve ser DIARIO ou SEMANAL"
            );
        }

        return habitoRepository.save(habito);
    }

    public List<Habito> buscarTodos() {
        return habitoRepository.findAll();
    }
}
