package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.TipoDominioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.filme.FilmeNaoEncontradoException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.ListaVaziaException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
import br.com.cwi.reset.gabrielaraujodesouza.model.Filme;
import br.com.cwi.reset.gabrielaraujodesouza.model.PersonagemAtor;
import br.com.cwi.reset.gabrielaraujodesouza.repository.FilmeRepository;
import br.com.cwi.reset.gabrielaraujodesouza.request.FilmeRequest;
import br.com.cwi.reset.gabrielaraujodesouza.request.PersonagemRequest;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoFilme;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmeService {

    @Autowired
    private EstudioService estudioService;
    @Autowired
    private DiretorService diretorService;
    @Autowired
    private PersonagemService personagemService;
    @Autowired
    private FilmeRepository filmeRepository;

    public void criarFilme(FilmeRequest filmeRequest) throws Exception {

        new ValidacaoFilme().accept(filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(),
                filmeRequest.getCapaFilme(),
                filmeRequest.getGeneros(),
                filmeRequest.getIdDiretor(),
                filmeRequest.getIdEstudio(),
                filmeRequest.getResumo(),
                filmeRequest.getPersonagens());

        Diretor diretor = this.diretorService.consultarDiretor(filmeRequest.getIdDiretor());
        Estudio estudio = this.estudioService.consultarEstudio(filmeRequest.getIdEstudio());

        List<PersonagemAtor> personagemAtor = this.personagemService.criarPersonagem(filmeRequest.getPersonagens());

        Filme filme = new Filme(
                filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(),
                filmeRequest.getCapaFilme(),
                filmeRequest.getGeneros(),
                estudio,
                diretor,
                personagemAtor,
                filmeRequest.getResumo());
        filmeRepository.save(filme);

    }
//
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
//        //duplica a lista de filmes existentes
//        List filmesFiltrados = new ArrayList(filmesCadastrados);
//
//        for(Filme filme : filmesCadastrados) {
//            final boolean containsFilterNomeFilme = filme.getNome().toLowerCase(Locale.ROOT).contains(nomeFilme.toLowerCase(Locale.ROOT));
//            if(!containsFilterNomeFilme){
//                if(filmesFiltrados.contains(filme)){
//                    filmesFiltrados.remove(filme);
//                }
//            }
//            final boolean containsFilterNomeDiretor = filme.getDiretor().getNome().toLowerCase(Locale.ROOT).contains(nomeDiretor.toLowerCase(Locale.ROOT));
//            if(!containsFilterNomeDiretor){
//                if(filmesFiltrados.contains(filme)){
//                    filmesFiltrados.remove(filme);
//                }
//            }
//            boolean contemPersonagem = false;
//            boolean contemAtor = false;
//            for (PersonagemAtor personagem : filme.getPersonagens()) {
//                final boolean containsFilterNomePersonagem = personagem.getNomePersonagem().toLowerCase(Locale.ROOT).contains(nomePersonagem.toLowerCase(Locale.ROOT));
//                if(containsFilterNomePersonagem){
//                    contemPersonagem = true;
//                }
//                final boolean containsFilterNomeAtor = personagem.getAtor().getNome().toLowerCase(Locale.ROOT).contains(nomeAtor.toLowerCase(Locale.ROOT));
//                if(containsFilterNomeAtor){
//                    contemAtor = true;
//                }
//            }
//            if(!contemPersonagem) {
//                if(filmesFiltrados.contains(filme)){
//                    filmesFiltrados.remove(filme);
//                }
//            }
//            if(!contemAtor) {
//                if(filmesFiltrados.contains(filme)){
//                    filmesFiltrados.remove(filme);
//                }
//            }
//        }
//
//        if (filmesFiltrados.isEmpty()){
//            throw new FilmeNaoEncontradoException(nomeFilme,nomeDiretor,nomePersonagem,nomeAtor);
//        }
//
//        return filmesFiltrados;
//    }
}
