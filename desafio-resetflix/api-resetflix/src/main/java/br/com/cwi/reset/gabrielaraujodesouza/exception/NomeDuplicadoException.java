package br.com.cwi.reset.gabrielaraujodesouza.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NomeDuplicadoException extends Exception{

    public NomeDuplicadoException(String classe, String nome) {
        super(String.format("Já existe um %s cadastrado para o nome %s.", classe, nome));
    }
}
