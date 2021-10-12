package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class SemSobrenomeException extends Exception{

    public SemSobrenomeException(String nomeClasse) {

        super(String.format("Deve ser informado no mínimo nome e sobrenome para o %s.",nomeClasse));
    }
}
