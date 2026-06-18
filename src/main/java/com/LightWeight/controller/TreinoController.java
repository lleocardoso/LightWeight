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

    //Ponto de entrada HTTP REST da aplicação para recursos de Treino.
    //Responsável estritamente pelo protocolo de transporte, validação de payloads e direcionamento semântico HTTP.
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
            @RequestBody @Valid TreinoRequestDTO dto) {// @Valid garante o acionamento do validador de contratos
        TreinoResponseDTO response = treinoService.create(usuarioId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);// Retorna 201.
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
        return ResponseEntity.noContent().build(); // Retorna Status 204
    }

    // Aciona a regra de negócio matemática de atualização de tonelagem.
    @PutMapping("/{id}/calcular-volume")
    public ResponseEntity<BigDecimal> calcularVolume(@PathVariable UUID id) {
        BigDecimal volumeTotal = treinoService.calcularVolumeTotal(id);
        return ResponseEntity.ok(volumeTotal);
    }

    //Implementação da rota de ativação de treino.
    @PutMapping("/{id}/ativar")
    public ResponseEntity<TreinoResponseDTO> ativar(@PathVariable UUID id) {
        TreinoResponseDTO response = treinoService.ativarTreino(id);
        return ResponseEntity.ok(response);
    }
}