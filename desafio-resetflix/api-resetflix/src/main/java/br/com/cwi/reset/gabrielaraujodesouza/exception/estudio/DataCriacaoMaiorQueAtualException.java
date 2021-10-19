package br.com.cwi.reset.gabrielaraujodesouza.exception.estudio;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataCriacaoMaiorQueAtualException extends Exception{

    public DataCriacaoMaiorQueAtualException(String nomeClasse) {
        super(String.format("Não é possível cadastrar %s do futuro.",nomeClasse));
    }

}
