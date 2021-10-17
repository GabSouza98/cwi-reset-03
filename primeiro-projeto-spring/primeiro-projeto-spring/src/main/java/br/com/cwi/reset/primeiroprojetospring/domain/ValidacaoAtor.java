package br.com.cwi.reset.primeiroprojetospring.domain;

import br.com.cwi.reset.primeiroprojetospring.FakeDatabase;

import java.time.LocalDate;
import java.util.Locale;

public class ValidacaoAtor {

    public void accept(final String nome, final LocalDate dataNascimento, final Integer anoInicioAtividade, final StatusCarreira statusCarreira) throws Exception {

        if (nome == null) {
//            throw new NomeVazioException();
        }

        if (dataNascimento == null) {
//            throw new DataNascimentoVazioException();
        }

        if (anoInicioAtividade == null) {
//            throw new AnoInicioAtividadeVazioException();
        }

        if (statusCarreira == null) {
//            throw new StatusCarreiraVazioException();
        }

        if (nome.split(" ").length < 2) {
//            throw new SemSobrenomeException(tipoDominioException.getSingular());
        }

        if (LocalDate.now().isBefore(dataNascimento)) {
//            throw new DataNascimentoMaiorQueAtualException(tipoDominioException.getPlural());
        }

        if (anoInicioAtividade <= dataNascimento.getYear()) {
//            throw new AnoInicioAtividadoAntesDeDataNascimentoException(tipoDominioException.getSingular());
        }

        for(Ator a : FakeDatabase.getInstance().recuperaAtores()){
            if (a.getNome().toLowerCase(Locale.ROOT).equals(nome.toLowerCase(Locale.ROOT))) {
//                throw new NomeDuplicadoException(TipoDominioException.ATOR.singular, nome);
            }
        }
    }
}
