package br.com.cwi.reset.gabrielaraujodesouza.validator;

import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor.*;

import java.time.LocalDate;

public class ValidacaoAtor {

    public void accept(final String nome, final LocalDate dataNascimento, final Integer anoInicioAtividade, final TipoDominioException tipoDominioException) throws Exception {

        if (nome.split(" ").length < 2) {
            throw new SemSobrenomeException(tipoDominioException.getSingular());
        }

        if (anoInicioAtividade <= dataNascimento.getYear()) {
            throw new AnoInicioAtividadoAntesDeDataNascimentoException(tipoDominioException.getSingular());
        }
    }
}
