package br.com.cwi.reset.gabrielaraujodesouza.exception.filme;

import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.CampoVazioException;

public class IdDiretorNullException extends CampoVazioException {

    public IdDiretorNullException() {
        super("idDiretor");
    }
}
