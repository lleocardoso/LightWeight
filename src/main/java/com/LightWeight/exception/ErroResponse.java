package com.LightWeight.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErroResponse {
    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String message;
    private String caminho;
}
