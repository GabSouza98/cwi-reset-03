package br.com.cwi.reset.gabrielaraujodesouza.validator;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor.NomeVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.estudio.StatusAtividadeVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.filme.*;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.NomeDuplicadoException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
import br.com.cwi.reset.gabrielaraujodesouza.model.Genero;
import br.com.cwi.reset.gabrielaraujodesouza.request.PersonagemRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ValidacaoFilme {

    public void accept(final String nome,
                       final Integer anoLancamento,
                       final String capaFilme,
                       final List<Genero> generos,
                       final Integer idDiretor,
                       final Integer idEstudio,
                       final String resumo,
                       final List<PersonagemRequest> personagens) throws Exception {

        if (nome == null) {
            throw new NomeVazioException();
        }

        if (anoLancamento == null) {
            throw new AnoLancamentoVazioException();
        }

        if (capaFilme == null) {
            throw new CapaFilmeVazioException();
        }

        if (generos == null ) {
            throw new ListaGenerosNulaException();
        } else if (generos.isEmpty()) {
            throw new ListaGenerosVaziaException();
        }

        //verifica duplicidade de genero
        List<Genero> generoAux = new ArrayList<>();

        for (Genero g : generos) {
            if(generoAux.contains(g)) {
                throw new GeneroDuplicadoException();
            } else {
                generoAux.add(g);
            }
        }

        if (idDiretor == null) {
            throw new NomeVazioException();
        }




        )








        if (statusAtividade == null) {
            System.out.println("faltou status");
            throw new StatusAtividadeVazioException();
        }



        for (Estudio e : FakeDatabase.getInstance().recuperaEstudios()) {
            if (e.getNome().toLowerCase(Locale.ROOT).equals(nome.toLowerCase(Locale.ROOT))) {
                throw new NomeDuplicadoException(tipoDominioException.getSingular(), nome);
            }
        }
    }
}
