package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class AtorDuplicadoException extends Exception{

    public AtorDuplicadoException() {
        super("Tentativa de cadastrar 2 nomes iguais.");
    }
}
