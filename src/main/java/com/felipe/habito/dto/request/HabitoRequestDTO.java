package com.felipe.habito.dto.request;

import com.felipe.habito.enums.Frequencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HabitoRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,
        @Size(max = 300, message = "Nome deve ter no máximo 300 caracteres")
        String descricao,
        @NotNull(message = "Frequência é obrigatória")
        Frequencia frequencia
) {
}