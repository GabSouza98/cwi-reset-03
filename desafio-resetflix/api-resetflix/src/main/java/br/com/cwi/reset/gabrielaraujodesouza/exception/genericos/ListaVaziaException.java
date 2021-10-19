package br.com.cwi.reset.gabrielaraujodesouza.exception.genericos;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ListaVaziaException extends Exception{

    public ListaVaziaException(String classe, String classePlural) {
        super(String.format("Nenhum %s cadastrado, favor cadastar %s.",classe,classePlural));

    }
}
