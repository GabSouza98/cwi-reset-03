package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class ListaVaziaException extends Exception{

    public ListaVaziaException(String classe, String classePlural) {
        super(String.format("Nenhum %s cadastrado, favor cadastar %s.",classe,classePlural));

    }
}
