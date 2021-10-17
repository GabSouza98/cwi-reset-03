package br.com.cwi.reset.gabrielaraujodesouza.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FiltroException extends Exception{
    public FiltroException(String classe, String filtro){
        super(String.format("%s não encontrado com o filtro %s, favor informar outro filtro.", classe,filtro));
    }
}
