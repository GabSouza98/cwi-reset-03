package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class StatusCarreiraNull extends Exception{

    public StatusCarreiraNull() {
        super("Campo obrigatório não informado. Favor informar o campo Status Carreira");
    }
}
