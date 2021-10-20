package br.com.cwi.reset.gabrielaraujodesouza.exception.filme;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ListaPersonagensVaziaException extends Exception{

    public ListaPersonagensVaziaException() {
        super("A lista de personagens informada está vazia");
    }
}
