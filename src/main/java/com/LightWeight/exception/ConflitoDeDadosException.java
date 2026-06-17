package com.LightWeight.exception;

    //Lançada quando ocorre uma violação de regra de negócio, como e-mail duplicado ou treinos conflitantes. Retorna 409.
public class ConflitoDeDadosException extends RuntimeException {
    public ConflitoDeDadosException(String mensagem) {
        super(mensagem);
    }
}
