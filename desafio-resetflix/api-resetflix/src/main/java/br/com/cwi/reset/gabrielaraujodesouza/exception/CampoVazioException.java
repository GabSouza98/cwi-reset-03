package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class CampoVazioException extends Exception{

    public CampoVazioException(final String campo) {
        super(String.format("Campo obrigatório não informado. Favor informar o campo ",campo));
    }
}
