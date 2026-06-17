package com.LightWeight.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

    //Interceptador global de erros da API.
    //Captura qualquer exceção lançada na camada de serviço ou controle, centralizando a resposta em um formato limpo e padronizado (ErroResponse) com o código HTTP adequado.

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata falhas de buscas que não retornaram resultados (404).
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleRecursoNaoEncontrado(
            RecursoNaoEncontradoException ex, HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, "Não Encontrado", ex.getMessage(), request.getRequestURI());
    }

    // Trata tentativas de cadastros duplicados ou conflitos lógicos (409).
    @ExceptionHandler(ConflitoDeDadosException.class)
    public ResponseEntity<ErroResponse> handleConflitodeDados(
            ConflitoDeDadosException ex, HttpServletRequest request) {
        return build (HttpStatus.CONFLICT, "Conflito", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(EstadoInvalidoException.class)
    public ResponseEntity<ErroResponse> handleEstadoInvalido(
            EstadoInvalidoException ex, HttpServletRequest request){
        return build(HttpStatus.CONFLICT, "Estado Invalido", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(EntidadeNaoProcessavelException.class)
    public ResponseEntity<ErroResponse> handleEntidadeNaoProcessavel(
            EntidadeNaoProcessavelException ex, HttpServletRequest request) {
            ex.printStackTrace();
        return build(HttpStatus.UNPROCESSABLE_ENTITY, "Entidade Não Processável", ex.getMessage(), request.getRequestURI());
    }

    // Captura falhas de validação de dados enviadas pelo cliente no Bean Validation (400).
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return build(HttpStatus.BAD_REQUEST, "Validação", mensagem, request.getRequestURI());
    }

    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErroResponse> handleMethodNotSupported(
            org.springframework.web.HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        return build(HttpStatus.METHOD_NOT_ALLOWED, "Método Não Permitido",
                "O método " + ex.getMethod() + " não é suportado para esta rota.", request.getRequestURI());
    }

    // Fallback para qualquer outro erro não mapeado no sistema (500).
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErroResponse> handleGeneric (
                Exception ex, HttpServletRequest request){
            ex.printStackTrace();
            return build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro Interno", "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.",
                    request.getRequestURI());
        }

        private ResponseEntity<ErroResponse> build (HttpStatus status, String erro, String mensagem, String caminho){
            ErroResponse body = new ErroResponse(LocalDateTime.now(), status.value(), erro, mensagem, caminho);
            return ResponseEntity.status(status).body(body);
        }

    }