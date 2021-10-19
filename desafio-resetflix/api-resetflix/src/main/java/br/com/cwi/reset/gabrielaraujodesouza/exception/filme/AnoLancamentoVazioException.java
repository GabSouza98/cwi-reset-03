package br.com.cwi.reset.gabrielaraujodesouza.exception.filme;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.CampoVazioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnoLancamentoVazioException extends CampoVazioException {

    public AnoLancamentoVazioException() {
        super("anoLancamento");
    }
}
