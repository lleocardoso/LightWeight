package com.LightWeight.controller;

import com.LightWeight.dto.request.TreinoRequestDTO;
import com.LightWeight.dto.response.TreinoResponseDTO;
import com.LightWeight.service.TreinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/treinos")
@RequiredArgsConstructor
public class TreinoController {

    private final TreinoService treinoService;

    @GetMapping
    public ResponseEntity<List<TreinoResponseDTO>> findAll() {
        List<TreinoResponseDTO> responses = treinoService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<TreinoResponseDTO> create(
            @PathVariable UUID usuarioId,
            @RequestBody @Valid TreinoRequestDTO dto) {

        TreinoResponseDTO response = treinoService.create(usuarioId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TreinoResponseDTO>> findAllByUsuario(@PathVariable UUID usuarioId) {
        List<TreinoResponseDTO> responses = treinoService.findAllByUsuario(usuarioId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoResponseDTO> findById(@PathVariable UUID id) {
        TreinoResponseDTO response = treinoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        treinoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/calcular-volume")
    public ResponseEntity<BigDecimal> calcularVolume(@PathVariable UUID id) {
        BigDecimal volumeTotal = treinoService.calcularVolumeTotal(id);
        return ResponseEntity.ok(volumeTotal);
    }
}