package com.felipe.habito.repository;

import com.felipe.habito.model.Habito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitoRepository extends JpaRepository<Habito, Long> {
}
