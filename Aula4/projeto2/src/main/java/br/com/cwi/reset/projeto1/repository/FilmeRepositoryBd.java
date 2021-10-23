package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Filme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepositoryBd extends CrudRepository<Filme, Integer> {
    Filme findByNome(String name);

    List<Filme> findAll();
}
