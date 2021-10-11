package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class CampoVazioException extends Exception{

    public CampoVazioException(String campo) {
        super("Campo obrigatório não informado. Favor informar o campo " + campo);
    }
}
