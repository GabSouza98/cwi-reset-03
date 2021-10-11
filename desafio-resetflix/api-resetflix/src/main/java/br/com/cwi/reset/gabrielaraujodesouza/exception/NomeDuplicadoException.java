package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class NomeDuplicadoException extends Exception{

    public NomeDuplicadoException(String nome) {
        super(String.format("Já existe um diretor cadastrado para o nome %s.",nome));
    }
}
