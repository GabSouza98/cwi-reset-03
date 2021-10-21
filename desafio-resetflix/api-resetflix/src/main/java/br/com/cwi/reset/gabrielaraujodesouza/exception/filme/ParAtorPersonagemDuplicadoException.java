package br.com.cwi.reset.gabrielaraujodesouza.exception.filme;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParAtorPersonagemDuplicadoException extends Exception{
    public ParAtorPersonagemDuplicadoException() {
        super("Não é permitido informar o mesmo ator/personagem mais de uma vez para o mesmo filme.");
    }
}
