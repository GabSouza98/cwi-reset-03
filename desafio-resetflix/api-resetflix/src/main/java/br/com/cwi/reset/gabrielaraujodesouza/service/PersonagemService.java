package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.CampoVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.IdException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.TipoDominioException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
import br.com.cwi.reset.gabrielaraujodesouza.model.PersonagemAtor;
import br.com.cwi.reset.gabrielaraujodesouza.request.PersonagemRequest;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoPersonagem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonagemService {

    private static Integer id = 1;
    private FakeDatabase fakeDatabase;
    private AtorService atorService;

    public PersonagemService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    public List<PersonagemAtor> criarPersonagem(List<PersonagemRequest> personagens) throws Exception {

        List<PersonagemAtor> listaPersonagemAtor = new ArrayList<>();

        for(PersonagemRequest personagemRequest : personagens) {
            new ValidacaoPersonagem().accept(personagemRequest.getIdAtor(),
                    personagemRequest.getNomePersonagem(),
                    personagemRequest.getDescricaoPersonagem(),
                    personagemRequest.getTipoAtuacao());
            Ator atorTeste = this.atorService.consultarAtor(personagemRequest.getIdAtor());
        }

        for(PersonagemRequest personagemRequest : personagens) {
            Ator atorValidado = this.atorService.consultarAtor(personagemRequest.getIdAtor());
            PersonagemAtor personagemAtor = new PersonagemAtor(id++,
                    atorValidado,
                    personagemRequest.getNomePersonagem(),
                    personagemRequest.getDescricaoPersonagem(),
                    personagemRequest.getTipoAtuacao());
            fakeDatabase.persistePersonagem(personagemAtor);
            listaPersonagemAtor.add(personagemAtor);
        }
        return listaPersonagemAtor;
    }

    public PersonagemAtor consultarPersonagem(Integer id) throws IdException, CampoVazioException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        List<PersonagemAtor> personagens = fakeDatabase.recuperaPersonagens();
        List<PersonagemAtor> personagensAux = personagens.stream()
                .filter(a -> a.getId() == id)
                .collect(Collectors.toList());

        if(personagensAux.size() == 1) {
            return personagensAux.get(0);
        } else {
            throw new IdException(TipoDominioException.PERSONAGEM.getSingular(), id);
        }
    }

}
