package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.model.Filme;
import br.com.cwi.reset.gabrielaraujodesouza.model.PersonagemAtor;
import br.com.cwi.reset.gabrielaraujodesouza.request.FilmeRequest;
import br.com.cwi.reset.gabrielaraujodesouza.request.PersonagemRequest;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoFilme;

import java.util.ArrayList;
import java.util.List;

public class FilmeService {

    private static Integer id = 1;
    private FakeDatabase fakeDatabase;
    private EstudioService estudioService;
    private DiretorService diretorService;
    private PersonagemService personagemService;

    public FilmeService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.estudioService = new EstudioService(FakeDatabase.getInstance());
        this.diretorService = new DiretorService(FakeDatabase.getInstance());
        this.personagemService = new PersonagemService(FakeDatabase.getInstance());
    }

    public void criarFilme(FilmeRequest filmeRequest) throws Exception {

        new ValidacaoFilme().accept(filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(),
                filmeRequest.getCapaFilme(),
                filmeRequest.getGeneros(),
                filmeRequest.getIdDiretor(),
                filmeRequest.getIdEstudio(),
                filmeRequest.getResumo(),
                filmeRequest.getPersonagens());

        List<PersonagemAtor> personagensDoFilme = new ArrayList<>();

        //Itera todos os PersonagemRequest pelo validador de criação de Personagens.
        for(PersonagemRequest personagemRequest : filmeRequest.getPersonagens()){
            this.personagemService.criarPersonagem(personagemRequest);
            personagensDoFilme.add(personagemService.consultarPersonagem(personagemService.IdUltimoPersonagemCadastrado()));
        }



        Filme filme = new Filme(id++,
                filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(),
                filmeRequest.getCapaFilme(),
                filmeRequest.getGeneros(),
                this.diretorService.consultarDiretor(filmeRequest.getIdDiretor()),
                this.estudioService.consultarEstudio(filmeRequest.getIdEstudio()),
                filmeRequest.getResumo(),
                personagensDoFilme);
    }



}
