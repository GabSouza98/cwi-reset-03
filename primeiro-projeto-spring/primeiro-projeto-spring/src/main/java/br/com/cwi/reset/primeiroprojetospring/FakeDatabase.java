package br.com.cwi.reset.primeiroprojetospring;

import br.com.cwi.reset.primeiroprojetospring.domain.Ator;
import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {

    private static FakeDatabase fakeDatabase = new FakeDatabase();

    public static FakeDatabase getInstance() {
        return fakeDatabase;
    }

    private FakeDatabase() {
    }

    private List<Ator> atores = new ArrayList<>();
    private List<Diretor> diretores = new ArrayList<>();
    private List<Filme> filmes = new ArrayList<>();

    public void persisteAtor(Ator ator) {
        atores.add(ator);
    }

    public List<Ator> recuperaAtores() {
        return atores;
    }

    public void persisteDiretor(Diretor diretor) {
        diretores.add(diretor);
    }

    public List<Diretor> recuperaDiretores() {
        return diretores;
    }

}
