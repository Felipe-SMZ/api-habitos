package com.felipe.habito.controller;

import com.felipe.habito.model.Habito;
import com.felipe.habito.service.HabitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitos")
public class HabitoController {

    private final HabitoService habitoService;

    public HabitoController(HabitoService habitoService) {
        this.habitoService = habitoService;
    }

    //POST
    @PostMapping
    public ResponseEntity<Habito> criarHabito(@RequestBody Habito habito) {
        Habito habitoSalvo = habitoService.salvar(habito);
        return ResponseEntity.status(HttpStatus.CREATED).body(habitoSalvo);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<Habito>> listarHabitos() {
        List<Habito> habitos =  habitoService.buscarTodos();
        return ResponseEntity.ok(habitos);
    }
}
