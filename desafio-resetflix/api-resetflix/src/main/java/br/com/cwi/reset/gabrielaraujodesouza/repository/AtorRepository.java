package br.com.cwi.reset.gabrielaraujodesouza.repository;

import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtorRepository extends CrudRepository<Ator,Integer> {

    Ator findByNome(String nome);
    Optional<Ator> findById(Integer id);

    Ator save(Ator ator);
    void delete(Ator ator);
    List<Ator> findAll();




}
