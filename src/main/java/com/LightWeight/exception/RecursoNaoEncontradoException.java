package com.LightWeight.exception;

    //Lançada quando o sistema tenta acessar um recurso (UUID) que não existe no banco de dados. Retorna 404.
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String message) {
        super(message);
    }
}
