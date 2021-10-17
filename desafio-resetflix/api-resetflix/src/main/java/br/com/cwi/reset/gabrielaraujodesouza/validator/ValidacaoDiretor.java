package br.com.cwi.reset.gabrielaraujodesouza.validator;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.model.StatusCarreira;

import java.time.LocalDate;
import java.util.Locale;

public class ValidacaoDiretor {

    public void accept(final String nome, final LocalDate dataNascimento, final Integer anoInicioAtividade, final TipoDominioException tipoDominioException) throws NomeVazioException, DataNascimentoVazioException, AnoInicioAtividadeVazioException, Exception {

        if (nome == null) {
            throw new NomeVazioException();
        }

        if (dataNascimento == null) {
            throw new DataNascimentoVazioException();
        }

        if (anoInicioAtividade == null) {
            throw new AnoInicioAtividadeVazioException();
        }

        if (nome.split(" ").length < 2) {
            throw new SemSobrenomeException(tipoDominioException.getSingular());
        }

        if (LocalDate.now().isBefore(dataNascimento)) {
            throw new DataNascimentoMaiorQueAtualException(tipoDominioException.getPlural());
        }

        if (anoInicioAtividade <= dataNascimento.getYear()) {
            throw new AnoInicioAtividadoAntesDeDataNascimentoException(tipoDominioException.getSingular());
        }

        for(Diretor d : FakeDatabase.getInstance().recuperaDiretores()){
            if (d.getNome().toLowerCase(Locale.ROOT).equals(nome.toLowerCase(Locale.ROOT))) {
                throw new NomeDuplicadoException(tipoDominioException.getSingular(), nome);
            }
        }
    }
}
