package br.com.cwi.reset.gabrielaraujodesouza.repository;

import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.model.StatusCarreira;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtorRepository extends CrudRepository<Ator,Integer> {

    Optional<Ator> findById(Integer id);
    Ator save(Ator ator);
    void delete(Ator ator);
    List<Ator> findAll();
    Ator findByNomeEqualsIgnoreCase(String nome);
    List<Ator> findByStatusCarreira (StatusCarreira statusCarreira);
    List<Ator> findByStatusCarreiraAndNomeContainingIgnoreCase (StatusCarreira statusCarreira, String nome);

}
