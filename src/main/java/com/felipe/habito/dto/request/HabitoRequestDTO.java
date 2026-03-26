package com.felipe.habito.dto.request;

import com.felipe.habito.enums.Frequencia;

public record HabitoRequestDTO(
        String nome,
        String descricao,
        Frequencia frequencia
) {
}