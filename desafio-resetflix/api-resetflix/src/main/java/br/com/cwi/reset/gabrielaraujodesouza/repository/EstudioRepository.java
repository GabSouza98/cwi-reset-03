package br.com.cwi.reset.gabrielaraujodesouza.repository;

import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudioRepository extends CrudRepository<Estudio,Integer> {

    Optional<Estudio> findById(Integer id);
    Estudio save(Estudio estudio);
    void delete(Estudio estudio);
    Estudio findByNomeEqualsIgnoreCase(String nome);
    List<Estudio> findAll();
    List<Estudio> findByNomeContainingIgnoreCase(String nome);
}
