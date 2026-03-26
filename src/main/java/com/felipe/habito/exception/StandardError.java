package com.felipe.habito.exception;

import java.time.Instant;
import java.util.List;

public record StandardError(
        Instant timestamp,
        Integer status,
        String error,
        List<String> message,
        String path) {
}
