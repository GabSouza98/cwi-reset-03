package br.com.cwi.reset.gabrielaraujodesouza.validator;

import br.com.cwi.reset.gabrielaraujodesouza.exception.estudio.DescricaoVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.personagemator.IdAtorVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.personagemator.NomePersonagemVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.personagemator.TipoAtuacaoVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.model.TipoAtuacao;

public class ValidacaoPersonagem {

    public void accept(final Integer idAtor, final String nomePersonagem, final String descricaoPersonagem, final TipoAtuacao tipoAtuacao) throws Exception {

        if (idAtor == null) {
            throw new IdAtorVazioException();
        }

        if (nomePersonagem == null) {
            throw new NomePersonagemVazioException();
        }

        if (descricaoPersonagem == null) {
            throw new DescricaoVazioException();
        }

        if (tipoAtuacao == null) {
            throw new TipoAtuacaoVazioException();
        }
    }
}
