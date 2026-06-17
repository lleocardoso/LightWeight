package com.LightWeight.exception;

    //Lançada quando uma operação não pode ser executada devido ao estado atual da entidade (Ex: Finalizar um treino INATIVO). Retorna 409.
public class EstadoInvalidoException extends RuntimeException {
    public EstadoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
