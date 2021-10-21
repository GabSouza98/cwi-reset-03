package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.TipoDominioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.filme.FilmeNaoEncontradoException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.ListaVaziaException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
import br.com.cwi.reset.gabrielaraujodesouza.model.Filme;
import br.com.cwi.reset.gabrielaraujodesouza.model.PersonagemAtor;
import br.com.cwi.reset.gabrielaraujodesouza.request.FilmeRequest;
import br.com.cwi.reset.gabrielaraujodesouza.request.PersonagemRequest;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoFilme;

import java.util.*;

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
        this.fakeDatabase.persisteFilme(filme);
    }

//    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws ListaVaziaException, FilmeNaoEncontradoException {
//
//        final List<Filme> filmesCadastrados = fakeDatabase.recuperaFilmes();
//
//        //verifica se há filme cadastrado
//        if (filmesCadastrados.isEmpty()) {
//            throw new ListaVaziaException(TipoDominioException.FILME.getSingular(), TipoDominioException.FILME.getPlural());
//        }
//
//        //verifica o caso mais simples, todos os parametros vazios
//        if (nomeFilme.isEmpty() &&
//                nomeDiretor.isEmpty() &&
//                nomePersonagem.isEmpty() &&
//                nomeAtor.isEmpty()){
//            return filmesCadastrados;
//        }
//
//        final List<Filme> filmesComFiltroNomeFilme = new ArrayList<>();
//        final List<Filme> filmesComFiltroNomeDiretor = new ArrayList<>();
//        final List<Filme> filmesComFiltroNomePersonagem = new ArrayList<>();
//        final List<Filme> filmesComFiltroNomeAtor = new ArrayList<>();
//        final List<Filme> filmesFiltrados = new ArrayList<>();//
//
//        for(Filme filme : filmesCadastrados) {
//                final boolean containsFilterNomeFilme = filme.getNome().toLowerCase(Locale.ROOT).contains(nomeFilme.toLowerCase(Locale.ROOT));
//                if(containsFilterNomeFilme){
//                    filmesComFiltroNomeFilme.add(filme);
//                }
//                final boolean containsFilterNomeDiretor = filme.getDiretor().getNome().toLowerCase(Locale.ROOT).contains(nomeDiretor.toLowerCase(Locale.ROOT));
//                if(containsFilterNomeDiretor){
//                    filmesComFiltroNomeDiretor.add(filme);
//                }
//            for (PersonagemAtor personagem : filme.getPersonagens()) {
//                    final boolean containsFilterNomePersonagem = personagem.getNomePersonagem().toLowerCase(Locale.ROOT).contains(nomePersonagem.toLowerCase(Locale.ROOT));
//                    if(containsFilterNomePersonagem){
//                        filmesComFiltroNomePersonagem.add(filme);
//                    }
//                    final boolean containsFilterNomeAtor = personagem.getAtor().getNome().toLowerCase(Locale.ROOT).contains(nomeAtor.toLowerCase(Locale.ROOT));
//                    if(containsFilterNomeAtor){
//                        filmesComFiltroNomeAtor.add(filme);
//                    }
//            }
//        }
//
//        for (Filme f : filmesCadastrados) {
//            if(filmesComFiltroNomeFilme.contains(f) &&
//            filmesComFiltroNomeDiretor.contains(f) &&
//            filmesComFiltroNomePersonagem.contains(f) &&
//            filmesComFiltroNomeAtor.contains(f)) {
//                filmesFiltrados.add(f);
//            }
//        }
//
//        if (filmesFiltrados.isEmpty()){
//            throw new FilmeNaoEncontradoException(nomeFilme,nomeDiretor,nomePersonagem,nomeAtor);
//        }
//
//        return filmesFiltrados;
//    }
    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws ListaVaziaException, FilmeNaoEncontradoException {

        final List<Filme> filmesCadastrados = fakeDatabase.recuperaFilmes();

        //verifica se há filme cadastrado
        if (filmesCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.FILME.getSingular(), TipoDominioException.FILME.getPlural());
        }

        //verifica o caso mais simples, todos os parametros vazios
        if (nomeFilme.isEmpty() &&
                nomeDiretor.isEmpty() &&
                nomePersonagem.isEmpty() &&
                nomeAtor.isEmpty()){
            return filmesCadastrados;
        }

        List filmesFiltrados = new ArrayList(filmesCadastrados);

        for(Filme filme : filmesCadastrados) {
            final boolean containsFilterNomeFilme = filme.getNome().toLowerCase(Locale.ROOT).contains(nomeFilme.toLowerCase(Locale.ROOT));
            if(!containsFilterNomeFilme){
                if(filmesFiltrados.contains(filme)){
                    filmesFiltrados.remove(filme);
                }
            }
            final boolean containsFilterNomeDiretor = filme.getDiretor().getNome().toLowerCase(Locale.ROOT).contains(nomeDiretor.toLowerCase(Locale.ROOT));
            if(!containsFilterNomeDiretor){
                if(filmesFiltrados.contains(filme)){
                    filmesFiltrados.remove(filme);
                }
            }
            boolean contemPersonagem = false;
            boolean contemAtor = false;
            for (PersonagemAtor personagem : filme.getPersonagens()) {
                final boolean containsFilterNomePersonagem = personagem.getNomePersonagem().toLowerCase(Locale.ROOT).contains(nomePersonagem.toLowerCase(Locale.ROOT));
                if(containsFilterNomePersonagem){
                    contemPersonagem = true;
                }
                final boolean containsFilterNomeAtor = personagem.getAtor().getNome().toLowerCase(Locale.ROOT).contains(nomeAtor.toLowerCase(Locale.ROOT));
                if(containsFilterNomeAtor){
                    contemAtor = true;
                }
            }
            if(!contemPersonagem) {
                if(filmesFiltrados.contains(filme)){
                    filmesFiltrados.remove(filme);
                }
            }
            if(!contemAtor) {
                if(filmesFiltrados.contains(filme)){
                    filmesFiltrados.remove(filme);
                }
            }
        }

        if (filmesFiltrados.isEmpty()){
            throw new FilmeNaoEncontradoException(nomeFilme,nomeDiretor,nomePersonagem,nomeAtor);
        }

        return filmesFiltrados;
    }
}
