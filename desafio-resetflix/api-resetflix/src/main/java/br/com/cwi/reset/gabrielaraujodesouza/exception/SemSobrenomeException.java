package br.com.cwi.reset.gabrielaraujodesouza.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SemSobrenomeException extends Exception{

    public SemSobrenomeException(String nomeClasse) {

        super(String.format("Deve ser informado no mínimo nome e sobrenome para o %s.",nomeClasse));
    }
}
