package br.com.cwi.reset.gabrielaraujodesouza.repository;

import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.model.Filme;
import br.com.cwi.reset.gabrielaraujodesouza.model.PersonagemAtor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends CrudRepository<Filme,Integer> {

    Filme findByIdEquals(Integer id);
    Filme save(Filme filme);
    void delete(Filme filme);
    Filme findByNomeEqualsIgnoreCase(String nome);
    List<Filme> findAll();

    List<Filme> findByNomeContainingIgnoreCase(String nome);
    List<Filme> findByDiretorNomeContainingIgnoreCase(String nomeDiretor);
//    List<Filme> findByNomePersonagemIn(String nome, List<PersonagemAtor> personagens);

    List<Filme> findByDiretor(Diretor diretor);
    List<Filme> findByPersonagenssNomePersonagemContainingIgnoreCase(String nomePersonagem);
    List<Filme> findByPersonagenssAtorNomeContainingIgnoreCase(String nomeAtor);

}
