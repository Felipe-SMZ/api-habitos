package com.felipe.habito.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Frequencia {
    DIARIO,
    SEMANAL;

    @JsonCreator
    public static Frequencia fromString(String value) {
        return Frequencia.valueOf(value.toUpperCase());
    }
}
