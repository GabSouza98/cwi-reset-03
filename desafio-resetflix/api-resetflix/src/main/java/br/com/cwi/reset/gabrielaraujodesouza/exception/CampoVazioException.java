package br.com.cwi.reset.gabrielaraujodesouza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CampoVazioException extends Exception{

    public CampoVazioException(final String campo) {
        super(String.format("Campo obrigatório não informado. Favor informar o campo ",campo));
    }
}
