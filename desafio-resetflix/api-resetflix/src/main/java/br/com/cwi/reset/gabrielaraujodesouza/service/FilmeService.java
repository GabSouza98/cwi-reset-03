package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.exception.TipoDominioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.filme.FilmeNaoEncontradoException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.IdException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.ListaVaziaException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
import br.com.cwi.reset.gabrielaraujodesouza.model.Filme;
import br.com.cwi.reset.gabrielaraujodesouza.model.PersonagemAtor;
import br.com.cwi.reset.gabrielaraujodesouza.repository.FilmeRepository;
import br.com.cwi.reset.gabrielaraujodesouza.request.FilmeRequest;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoFilme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.isNull;

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

//        public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws ListaVaziaException, FilmeNaoEncontradoException {
//
//        final List<Filme> filmesCadastrados = filmeRepository.findAll();
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

    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws ListaVaziaException, FilmeNaoEncontradoException {

        final List<Filme> filmesCadastrados = filmeRepository.findAll();

        //verifica se há filme cadastrado
        if (filmesCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.FILME.getSingular(), TipoDominioException.FILME.getPlural());
        }

        final List<Filme> filtrarNomePersonagem = filtrarNomePersonagem(filmesCadastrados, nomePersonagem);
        final List<Filme> filtrarNomeAtor = filtrarNomeAtor(filmesCadastrados, nomeAtor);
        final List<Filme> filtrarNomeDiretor = filtrarNomeDiretor(filmesCadastrados, nomeDiretor);
        final List<Filme> filtrarNomeFilme = filtrarNomeFilme(filmesCadastrados, nomeFilme);

        List<Filme> filtroFinal = new ArrayList<>();

        for (Filme f : filmesCadastrados){
            if (
            filtrarNomePersonagem.contains(f) && filtrarNomeAtor.contains(f) &&
            filtrarNomeDiretor.contains(f) && filtrarNomeFilme.contains(f)) {
                filtroFinal.add(f);
            }
        }

        if (filtroFinal.isEmpty()){
            throw new FilmeNaoEncontradoException(nomeFilme,nomeDiretor,nomePersonagem,nomeAtor);
        }

        return filtroFinal;
    }

    private List<Filme> filtrarNomeFilme(final List<Filme> listaOriginal, final String nome) {

        if (nome.isEmpty()) {
            return listaOriginal;
        }

        return filmeRepository.findByNomeContainingIgnoreCase(nome);

    }

    private List<Filme> filtrarNomeDiretor(final List<Filme> listaOriginal, final String nome) {

        if (nome.isEmpty()) {
            return listaOriginal;
        }

        return filmeRepository.findByDiretorNomeContainingIgnoreCase(nome);

    }

    private List<Filme> filtrarNomeAtor(final List<Filme> listaOriginal, final String nome) {
        if (nome.isEmpty()) {
            return listaOriginal;
        }

        return filmeRepository.findByPersonagenssAtorNomeContainingIgnoreCase(nome);
    }

    private List<Filme> filtrarNomePersonagem(final List<Filme> listaOriginal, final String nome) {
        if (nome.isEmpty()) {
            return listaOriginal;
        }

        return filmeRepository.findByPersonagenssNomePersonagemContainingIgnoreCase(nome);
    }

    public boolean consultarDiretor(Diretor diretor) {

        if(filmeRepository.findByDiretor(diretor).isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public void removerFilme(Integer id) throws IdException {

        Filme filmeCadastrado = filmeRepository.findByIdEquals(id);

        if(isNull(filmeCadastrado)) {
            throw new IdException("Filme", id);
        }
        //personagemService.deletarPersonagens(filmeCadastrado.getPersonagenss());
        filmeRepository.delete(filmeCadastrado);
    }
}
