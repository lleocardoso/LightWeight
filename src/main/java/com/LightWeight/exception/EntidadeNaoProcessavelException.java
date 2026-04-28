package com.LightWeight.exception;

public class EntidadeNaoProcessavelException extends RuntimeException {
    public EntidadeNaoProcessavelException(String mensagem) {
        super(mensagem);
    }
}
