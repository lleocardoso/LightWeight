package com.LightWeight.controller;

import com.LightWeight.dto.request.ExercicioRequestDTO;
import com.LightWeight.dto.response.ExercicioResponseDTO;
import com.LightWeight.service.ExercicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercicios")
@RequiredArgsConstructor
public class ExercicioController {

    private final ExercicioService exercicioService;

    // Rota para listar todos os exercícios cadastrados
    @GetMapping
    public ResponseEntity<List<ExercicioResponseDTO>> findAll() {
        return ResponseEntity.ok(exercicioService.findAll());
    }

    // Rota para cadastrar um exercício dentro de um treino específico
    @PostMapping("/treino/{treinoId}")
    public ResponseEntity<ExercicioResponseDTO> create(
            @PathVariable UUID treinoId,
            @RequestBody @Valid ExercicioRequestDTO dto) {
        ExercicioResponseDTO response = exercicioService.create(treinoId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}