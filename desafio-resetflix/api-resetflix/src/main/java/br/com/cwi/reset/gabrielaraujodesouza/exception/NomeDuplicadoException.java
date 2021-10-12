package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class NomeDuplicadoException extends Exception{

    public NomeDuplicadoException(String classe, String nome) {
        super(String.format("Já existe um %s cadastrado para o nome %s.", classe, nome));
    }
}
