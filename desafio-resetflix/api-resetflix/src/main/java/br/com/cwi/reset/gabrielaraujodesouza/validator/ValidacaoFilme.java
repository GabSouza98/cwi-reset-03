package br.com.cwi.reset.gabrielaraujodesouza.validator;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor.NomeVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.estudio.StatusAtividadeVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.filme.*;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.NomeDuplicadoException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
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

        for (PersonagemRequest p : personagens) {
            new ValidacaoPersonagem().accept(p.getIdAtor(),
                    p.getNomePersonagem(),
                    p.getDescricaoPersonagem(),
                    p.getTipoAtuacao());
        }

        // IMPLEMENTAÇÃO 1
        for(int i=0;i<personagens.size();i++){
            for(int j=0;j<personagens.size();j++) {
                if (i!=j) {
                    if (personagens.get(i).getIdAtor() == personagens.get(j).getIdAtor()) {
                        if(personagens.get(i).getNomePersonagem().toLowerCase(Locale.ROOT).equals(personagens.get(j).getNomePersonagem().toLowerCase(Locale.ROOT))) {
                            throw new ParAtorPersonagemDuplicadoException();
                        }
                    }
                }
            }
        }

        // IMPLEMENTAÇÃO 2
//        Set set = new HashSet();
//        for(PersonagemRequest pRequest : personagens) {
//            set.add(pRequest.getIdAtor().toString().concat(pRequest.getNomePersonagem()));
//        }
//        if (set.size() < personagens.size()) {
//            throw new ParAtorPersonagemDuplicadoException();
//        }


//






    }
}
