package com.felipe.habito.dto.response;

import com.felipe.habito.enums.Frequencia;
import com.felipe.habito.model.Habito;

public record HabitoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Frequencia frequencia,
        Boolean ativo) {
}