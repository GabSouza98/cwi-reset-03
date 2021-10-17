package br.com.cwi.reset.primeiroprojetospring.service;


import br.com.cwi.reset.primeiroprojetospring.FakeDatabase;
import br.com.cwi.reset.primeiroprojetospring.domain.Ator;
import br.com.cwi.reset.primeiroprojetospring.domain.ValidacaoAtor;
import br.com.cwi.reset.primeiroprojetospring.request.AtorRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AtorService {

    private static Integer id = 1;
    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) throws Exception {

        new ValidacaoAtor().accept(atorRequest.getNome(),atorRequest.getDataNascimento(),atorRequest.getAnoInicioAtividade(),atorRequest.getStatusCarreira());

        Ator ator = new Ator(id++,
                atorRequest.getNome(),
                atorRequest.getDataNascimento(),
                atorRequest.getAnoInicioAtividade(),
                atorRequest.getStatusCarreira());
        this.fakeDatabase.persisteAtor(ator);
    }


    public List<Ator> consultarAtores()  {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        if(atores.isEmpty()){
            return null;
        } else {
            return atores;
        }
    }
}
