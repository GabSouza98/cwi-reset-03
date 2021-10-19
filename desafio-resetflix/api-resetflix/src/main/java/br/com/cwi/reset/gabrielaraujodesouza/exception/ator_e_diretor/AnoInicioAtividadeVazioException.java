package br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor;

import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.CampoVazioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnoInicioAtividadeVazioException extends CampoVazioException {

    public AnoInicioAtividadeVazioException() {
        super("anoInicioAtividade");
    }
}
