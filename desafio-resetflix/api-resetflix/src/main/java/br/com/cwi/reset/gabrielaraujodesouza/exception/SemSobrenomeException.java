package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class SemSobrenomeException extends Exception{

    public SemSobrenomeException() {
        super("Deve ser informado no mínimo nome e sobrenome para o ator.");
    }

}
