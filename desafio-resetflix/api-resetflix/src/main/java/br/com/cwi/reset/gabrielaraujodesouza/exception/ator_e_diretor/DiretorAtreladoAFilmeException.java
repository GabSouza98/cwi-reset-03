package br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DiretorAtreladoAFilmeException extends Exception{

    public DiretorAtreladoAFilmeException() {
        super("Este diretor está vinculado a um ou mais filmes, para remover o diretor é necessário remover os seus filmes de participação.");
    }
}
