package br.com.cwi.reset.gabrielaraujodesouza.validator;

import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor.NomeVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.estudio.DataCriacaoMaiorQueAtualException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.estudio.DataCriacaoVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.estudio.DescricaoVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.estudio.StatusAtividadeVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.model.StatusAtividade;

import java.time.LocalDate;

public class ValidacaoEstudio {

    public void accept(final String nome, final String descricao, final LocalDate dataCriacao, final StatusAtividade statusAtividade, final TipoDominioException tipoDominioException) throws Exception {

        if (nome == null) {
            throw new NomeVazioException();
        }

        if (descricao == null) {
            throw new DescricaoVazioException();
        }

        if (dataCriacao == null) {
            throw new DataCriacaoVazioException();
        }

        if (statusAtividade == null) {
            throw new StatusAtividadeVazioException();
        }

        if (LocalDate.now().isBefore(dataCriacao)) {
            throw new DataCriacaoMaiorQueAtualException(tipoDominioException.getPlural());
        }
    }
}
