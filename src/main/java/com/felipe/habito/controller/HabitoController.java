package com.felipe.habito.controller;

import com.felipe.habito.dto.request.HabitoRequestDTO;
import com.felipe.habito.dto.response.HabitoResponseDTO;
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
    public ResponseEntity<HabitoResponseDTO> criarHabito(@RequestBody HabitoRequestDTO dto) {

        Habito habito = new Habito();
        habito.setNome(dto.nome());
        habito.setDescricao(dto.descricao());
        habito.setFrequencia(dto.frequencia());

        Habito salvo = habitoService.salvar(habito);

        HabitoResponseDTO response = new HabitoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getFrequencia(),
                salvo.getAtivo()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<HabitoResponseDTO>> listarHabitos() {

        List<HabitoResponseDTO> lista = habitoService.buscarTodos()
                .stream()
                .map(h -> new HabitoResponseDTO(
                        h.getId(),
                        h.getNome(),
                        h.getDescricao(),
                        h.getFrequencia(),
                        h.getAtivo()
                ))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitoResponseDTO> buscarPorId(@PathVariable Long id) {

        Habito h = habitoService.buscarPorId(id);

        HabitoResponseDTO response = new HabitoResponseDTO(
                h.getId(),
                h.getNome(),
                h.getDescricao(),
                h.getFrequencia(),
                h.getAtivo()
        );

        return ResponseEntity.ok(response);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<HabitoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody HabitoRequestDTO dto
    ) {

        Habito habito = new Habito();
        habito.setNome(dto.nome());
        habito.setDescricao(dto.descricao());
        habito.setFrequencia(dto.frequencia());

        Habito atualizado = habitoService.atualizar(id, habito);

        HabitoResponseDTO response = new HabitoResponseDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getDescricao(),
                atualizado.getFrequencia(),
                atualizado.getAtivo()
        );

        return ResponseEntity.ok(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Habito> deletarPorId(@PathVariable Long id) {
        habitoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
