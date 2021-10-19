package br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnoInicioAtividadoAntesDeDataNascimentoException extends Exception{

    public AnoInicioAtividadoAntesDeDataNascimentoException(String nomeClasse) {
        super(String.format("Ano de início de atividade inválido para o %s cadastrado.",nomeClasse));
    }
}
