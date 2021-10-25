package br.com.cwi.reset.gabrielaraujodesouza.repository;

import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.model.PersonagemAtor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonagemRepository extends CrudRepository<PersonagemAtor,Integer> {

    Optional<PersonagemAtor> findById(Integer id);
    PersonagemAtor save(PersonagemAtor personagemAtor);
    void delete(PersonagemAtor personagemAtor);
    PersonagemAtor findByNomePersonagemEqualsIgnoreCase(String nome);
    List<PersonagemAtor> findAll();
    List<PersonagemAtor> findByNomePersonagemContainingIgnoreCase(String nome);

}
