package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class SemSobrenomeDiretorException extends Exception{

    public SemSobrenomeDiretorException() {
        super("Deve ser informado no mínimo nome e sobrenome para o diretor.");
    }

}
