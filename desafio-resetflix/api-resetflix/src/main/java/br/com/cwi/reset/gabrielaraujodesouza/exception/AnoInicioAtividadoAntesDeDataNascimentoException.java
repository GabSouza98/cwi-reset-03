package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class AnoInicioAtividadoAntesDeDataNascimentoException extends Exception{

    public AnoInicioAtividadoAntesDeDataNascimentoException() {
        super("Ano de início de atividade inválido para o ator cadastrado.");
    }
}
