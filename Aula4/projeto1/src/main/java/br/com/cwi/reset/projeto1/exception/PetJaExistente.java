package br.com.cwi.reset.projeto1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PetJaExistente extends Exception{
    public PetJaExistente(String nome) {
        super("Pet já existente com o nome " + nome);
    }
}
