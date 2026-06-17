package com.LightWeight.exception;

    //Lançada quando a requisição é sintaticamente correta, mas semanticamente falha na validação das entidades. Retorna 422.
public class EntidadeNaoProcessavelException extends RuntimeException {
    public EntidadeNaoProcessavelException(String mensagem) {
        super(mensagem);
    }
}
