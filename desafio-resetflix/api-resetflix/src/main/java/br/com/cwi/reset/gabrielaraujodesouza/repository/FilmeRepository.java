package br.com.cwi.reset.gabrielaraujodesouza.repository;

import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.model.Filme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends CrudRepository<Filme,Integer> {

    Optional<Filme> findById(Integer id);
    Filme save(Filme filme);
    void delete(Filme filme);
    Filme findByNomeEqualsIgnoreCase(String nome);
    List<Filme> findAll();
    List<Filme> findByNomeContainingIgnoreCase(String nome);

}
