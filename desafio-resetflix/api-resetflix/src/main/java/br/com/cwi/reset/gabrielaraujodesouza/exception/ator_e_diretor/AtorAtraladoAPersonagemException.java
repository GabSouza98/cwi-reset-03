package br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AtorAtraladoAPersonagemException extends Exception{

    public AtorAtraladoAPersonagemException() {
        super("Este ator está vinculado a um ou mais personagens, para remover o ator é necessário remover os seus personagens de atuação.");
    }
}
