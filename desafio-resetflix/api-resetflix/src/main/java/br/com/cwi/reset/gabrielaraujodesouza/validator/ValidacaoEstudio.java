package br.com.cwi.reset.gabrielaraujodesouza.validator;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
import br.com.cwi.reset.gabrielaraujodesouza.model.StatusAtividade;

import java.time.LocalDate;
import java.util.Locale;

public class ValidacaoEstudio {

    public void accept(final String nome, final String descricao, final LocalDate dataCriacao, final StatusAtividade statusAtividade, final TipoDominioException tipoDominioException) throws Exception {

        if (nome == null) {
            System.out.println("faltou nome");
            throw new NomeVazioException();
        }

        if (descricao == null) {
            System.out.println("faltou descricao");
            throw new DescricaoVazioException();
        }

        if (dataCriacao == null) {
            System.out.println("faltou data");
            throw new DataCriacaoVazioException();
        }

        if (statusAtividade == null) {
            System.out.println("faltou status");
            throw new StatusAtividadeVazioException();
        }

        if (LocalDate.now().isBefore(dataCriacao)) {
            throw new DataCriacaoMaiorQueAtualException(tipoDominioException.getPlural());
        }

        for (Estudio e : FakeDatabase.getInstance().recuperaEstudios()) {
            if (e.getNome().toLowerCase(Locale.ROOT).equals(nome.toLowerCase(Locale.ROOT))) {
                throw new NomeDuplicadoException(tipoDominioException.getSingular(), nome);
            }
        }
    }
}
