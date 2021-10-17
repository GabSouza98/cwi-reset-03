package br.com.cwi.reset.gabrielaraujodesouza.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataNascimentoVazioException extends CampoVazioException{

    public DataNascimentoVazioException() {
        super("dataNascimento");
    }
}
