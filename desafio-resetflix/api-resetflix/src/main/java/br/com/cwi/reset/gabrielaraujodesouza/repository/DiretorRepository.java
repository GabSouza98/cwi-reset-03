package br.com.cwi.reset.gabrielaraujodesouza.repository;

import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.model.StatusCarreira;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiretorRepository extends CrudRepository<Diretor,Integer> {

    Optional<Diretor> findById(Integer id);
    Diretor save(Diretor diretor);
    void delete(Diretor diretor);
    Diretor findByNomeEqualsIgnoreCase(String nome);
    List<Diretor> findAll();
    List<Diretor> findByNomeContainingIgnoreCase(String nome);

}
