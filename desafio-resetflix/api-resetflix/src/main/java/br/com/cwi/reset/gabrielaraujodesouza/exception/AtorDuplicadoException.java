package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class AtorDuplicadoException extends Exception{

    public AtorDuplicadoException() {
        super("Já existe um ator cadastrado com este nome.");
    }
}
