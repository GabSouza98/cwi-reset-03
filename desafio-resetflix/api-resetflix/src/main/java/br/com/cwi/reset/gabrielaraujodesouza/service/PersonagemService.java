package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.IdException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.TipoDominioException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.model.PersonagemAtor;
import br.com.cwi.reset.gabrielaraujodesouza.request.PersonagemRequest;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoPersonagem;

public class PersonagemService {

    private static Integer id = 1;
    private FakeDatabase fakeDatabase;

    public PersonagemService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public Ator procuraAtorPorId(Integer idAtor) throws IdException {

        Ator atorProcurado = null;
        for (Ator ator : fakeDatabase.recuperaAtores()) {
            if (ator.getId() == idAtor){
                atorProcurado = ator;
            }
        }
        if (atorProcurado==null) {
            throw new IdException(TipoDominioException.ATOR.getSingular(), idAtor);
        } else {
            return atorProcurado;
        }
    }

    public void criarPersonagem(PersonagemRequest personagemRequest) throws Exception {

        new ValidacaoPersonagem().accept(personagemRequest.getIdAtor(),
                personagemRequest.getNomePersonagem(),
                personagemRequest.getDescricaoPersonagem(),
                personagemRequest.getTipoAtuacao());

        PersonagemAtor personagemAtor = new PersonagemAtor(id++,
                procuraAtorPorId(personagemRequest.getIdAtor()),
                personagemRequest.getNomePersonagem(),
                personagemRequest.getDescricaoPersonagem(),
                personagemRequest.getTipoAtuacao());
        fakeDatabase.persistePersonagem(personagemAtor);
    }
}
