package br.com.cwi.reset.gabrielaraujodesouza.validator;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor.*;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.NomeDuplicadoException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;

import java.time.LocalDate;
import java.util.Locale;

public class ValidacaoDiretor {

    public void accept(final String nome, final LocalDate dataNascimento, final Integer anoInicioAtividade, final TipoDominioException tipoDominioException) throws Exception {

        if (nome.split(" ").length < 2) {
            throw new SemSobrenomeException(tipoDominioException.getSingular());
        }

        if (anoInicioAtividade <= dataNascimento.getYear()) {
            throw new AnoInicioAtividadoAntesDeDataNascimentoException(tipoDominioException.getSingular());
        }

    }
}
