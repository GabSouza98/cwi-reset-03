package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.exception.filme.ParAtorPersonagemDuplicadoException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.model.PersonagemAtor;
import br.com.cwi.reset.gabrielaraujodesouza.repository.PersonagemRepository;
import br.com.cwi.reset.gabrielaraujodesouza.request.PersonagemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonagemService {

    @Autowired
    private AtorService atorService;
    @Autowired
    private PersonagemRepository personagemRepository;

    public List<PersonagemAtor> criarPersonagem(List<PersonagemRequest> personagens) throws Exception {

        List<PersonagemAtor> listaPersonagemAtor = new ArrayList<>();

        final Set<PersonagemRequest> personagemRequestSet = new HashSet<>();

        for(PersonagemRequest personagemRequest : personagens) {

            Ator validaAtor = this.atorService.consultarAtor(personagemRequest.getIdAtor());
            if (personagemRequestSet.contains(personagemRequest)) {
                throw new ParAtorPersonagemDuplicadoException();
            } else {
                personagemRequestSet.add(personagemRequest);
            }
        }

        for(PersonagemRequest personagemRequest : personagens) {
            PersonagemAtor personagemAtor = new PersonagemAtor(
                    this.atorService.consultarAtor(personagemRequest.getIdAtor()),
                    personagemRequest.getNomePersonagem(),
                    personagemRequest.getDescricaoPersonagem(),
                    personagemRequest.getTipoAtuacao());
            personagemRepository.save(personagemAtor);
            listaPersonagemAtor.add(personagemAtor);
        }
        return listaPersonagemAtor;
    }

    public boolean consultarPersonagemAtor(Ator ator) {

        if (personagemRepository.findByAtor(ator).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void deletarPersonagens(List<PersonagemAtor> personagens) {
        for (PersonagemAtor personagem : personagens){
            personagemRepository.delete(personagem);
        }
    }
}
