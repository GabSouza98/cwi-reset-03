package br.com.cwi.reset.gabrielaraujodesouza.validator;

import br.com.cwi.reset.gabrielaraujodesouza.exception.filme.*;
import br.com.cwi.reset.gabrielaraujodesouza.model.Genero;
import br.com.cwi.reset.gabrielaraujodesouza.request.PersonagemRequest;

import java.util.*;

public class ValidacaoFilme {

    public void accept(final String nome,
                       final Integer anoLancamento,
                       final String capaFilme,
                       final List<Genero> generos,
                       final Integer idDiretor,
                       final Integer idEstudio,
                       final String resumo,
                       final List<PersonagemRequest> personagens) throws Exception {


        if (generos.isEmpty()) {
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

        if (personagens.isEmpty()) {
            throw new ListaPersonagensVaziaException();
        }
    }
}
