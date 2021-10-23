package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.domain.Pet;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface AtorRepository extends CrudRepository<Ator,Integer> {

    Ator findByNome(String nome);
    List<Ator> findByNumeroOscars(Integer numero);
    Ator save(Ator ator);
    void delete(Ator ator);
    List<Ator> findAll();

    List<Ator> findByNumeroOscarsGreaterThanAndDataNascimentoGreaterThan(Integer numeroOscars, LocalDate dataNascimento);


}
