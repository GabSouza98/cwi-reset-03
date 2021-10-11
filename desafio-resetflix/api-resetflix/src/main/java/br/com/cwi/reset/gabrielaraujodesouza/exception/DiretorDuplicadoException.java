package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class DiretorDuplicadoException extends Exception{

    public DiretorDuplicadoException() {
        super("Tentativa de cadastrar 2 nomes iguais.");
    }
}
