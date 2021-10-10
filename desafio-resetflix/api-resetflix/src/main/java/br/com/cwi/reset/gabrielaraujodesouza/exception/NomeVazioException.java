package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class NomeVazioException extends Exception {

    public NomeVazioException() {
        super("Campo obrigatório não informado. Favor informar o campo nome");
    }
}
