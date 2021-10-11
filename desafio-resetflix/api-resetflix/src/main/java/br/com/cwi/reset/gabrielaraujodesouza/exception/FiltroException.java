package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class FiltroException extends Exception{
    public FiltroException(String classe, String filtro){
        super(String.format("%s não encontrado com o filtro %s, favor informar outro filtro.", classe,filtro));
    }
}
