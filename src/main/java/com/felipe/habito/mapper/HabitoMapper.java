package com.felipe.habito.mapper;

import com.felipe.habito.dto.request.HabitoRequestDTO;
import com.felipe.habito.dto.response.HabitoResponseDTO;
import com.felipe.habito.model.Habito;
import org.springframework.stereotype.Component;

@Component
public class HabitoMapper {
    public HabitoResponseDTO toDTO(Habito habito) {
        return new HabitoResponseDTO(
                habito.getId(),
                habito.getNome(),
                habito.getDescricao(),
                habito.getFrequencia(),
                habito.getAtivo()
        );
    }

    public Habito toEntity(HabitoRequestDTO dto) {
        Habito habito = new Habito();
        habito.setNome(dto.nome());
        habito.setDescricao(dto.descricao());
        habito.setFrequencia(dto.frequencia());
        return habito;
    }
}
