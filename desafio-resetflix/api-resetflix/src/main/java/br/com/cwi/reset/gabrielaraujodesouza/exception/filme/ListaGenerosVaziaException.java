package br.com.cwi.reset.gabrielaraujodesouza.exception.filme;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ListaGenerosVaziaException extends Exception{
    public ListaGenerosVaziaException() {
        super("A lista de gêneros informada está vazia");
    }
}


