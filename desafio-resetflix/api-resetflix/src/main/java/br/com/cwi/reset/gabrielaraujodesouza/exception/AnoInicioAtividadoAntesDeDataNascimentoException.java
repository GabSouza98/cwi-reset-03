package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class AnoInicioAtividadoAntesDeDataNascimentoException extends Exception{

    public AnoInicioAtividadoAntesDeDataNascimentoException() {
        super("Tentativa de cadastrar Ano Inicio Atividade anterior à data de nascimento.");
    }
}
