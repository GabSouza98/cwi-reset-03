package br.com.cwi.reset.projeto1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AtorJaExistente extends Exception{
    public AtorJaExistente(String nome) {
        super("Já existe um ator cadastrado com o nome " + nome);
    }
}
