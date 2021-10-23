package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.exception.AtorJaExistente;
import br.com.cwi.reset.projeto1.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    public Ator cadastrarAtor(Ator ator) throws AtorJaExistente {

        Ator atorJaExistente = atorRepository.findByNome(ator.getNome());

        if(atorJaExistente != null) {
            throw new AtorJaExistente(ator.getNome());
        }

        atorRepository.save(ator);
        return ator;
    }

    public List<Ator> consultarAtores() {
        return atorRepository.findAll();
    }

    public Ator consultarAtorPorNome(String nome) {
        return atorRepository.findByNome(nome);
    }

    public List<Ator> consultarPorNumeroOscars(Integer numeroOscars) {
        return atorRepository.findByNumeroOscars(numeroOscars);
    }

    public List<Ator> consultarPorFiltro(Integer numeroOscars, Integer anoNascimento) {
        LocalDate dataNascimento = LocalDate.of(anoNascimento, 1,1);
        return atorRepository.findByNumeroOscarsGreaterThanAndDataNascimentoGreaterThan(numeroOscars,dataNascimento);
    }
}
