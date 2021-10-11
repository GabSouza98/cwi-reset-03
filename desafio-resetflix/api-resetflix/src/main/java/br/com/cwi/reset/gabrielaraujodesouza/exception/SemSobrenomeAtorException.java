package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class SemSobrenomeAtorException extends Exception{

    public SemSobrenomeAtorException() {
        super("Deve ser informado no mínimo nome e sobrenome para o ator.");
    }

}
