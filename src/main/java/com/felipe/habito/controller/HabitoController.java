package com.felipe.habito.controller;

import com.felipe.habito.dto.request.HabitoRequestDTO;
import com.felipe.habito.dto.response.HabitoResponseDTO;
import com.felipe.habito.mapper.HabitoMapper;
import com.felipe.habito.model.Habito;
import com.felipe.habito.service.HabitoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitos")
public class HabitoController {

    private final HabitoService habitoService;
    private final HabitoMapper habitoMapper;

    public HabitoController(HabitoService habitoService, HabitoMapper habitoMapper) {
        this.habitoService = habitoService;
        this.habitoMapper = habitoMapper;
    }

    //POST
    @PostMapping
    public ResponseEntity<HabitoResponseDTO> criarHabito(@RequestBody @Valid HabitoRequestDTO dto) {

        Habito habitoEntity = habitoMapper.toEntity(dto);
        Habito salvo = habitoService.salvar(habitoEntity);
        HabitoResponseDTO habitoDTO = habitoMapper.toDTO(salvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(habitoDTO);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<HabitoResponseDTO>> listarHabitos() {

        List<HabitoResponseDTO> lista = habitoService.buscarTodos()
                .stream()
                .map(habitoMapper::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitoResponseDTO> buscarPorId(@PathVariable Long id) {

        Habito h = habitoService.buscarPorId(id);

        HabitoResponseDTO response = habitoMapper.toDTO(h);

        return ResponseEntity.ok(response);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<HabitoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid HabitoRequestDTO dto
    ) {

        Habito habito = habitoMapper.toEntity(dto);

        Habito atualizado = habitoService.atualizar(id, habito);

        HabitoResponseDTO response = habitoMapper.toDTO(atualizado);

        return ResponseEntity.ok(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        habitoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
