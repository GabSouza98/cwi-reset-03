package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.request.AtorRequest;
import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.model.StatusCarreira;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.response.AtorEmAtividade;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoAtor;

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

        new ValidacaoAtor().accept(atorRequest.getNome(),atorRequest.getDataNascimento(),atorRequest.getAnoInicioAtividade(),atorRequest.getStatusCarreira(),TipoDominioException.ATOR);

        Ator ator = new Ator(id++,
                    atorRequest.getNome(),
                    atorRequest.getDataNascimento(),
                    atorRequest.getAnoInicioAtividade(),
                    atorRequest.getStatusCarreira());
        this.fakeDatabase.persisteAtor(ator);
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade() throws ListaVaziaException {

        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atoresAux = atores.stream()
                .filter(a -> a.getStatusCarreira() == StatusCarreira.EM_ATIVIDADE)
                .collect(Collectors.toList());

        List<AtorEmAtividade> atoresEmAtividade = new ArrayList<>();

        for (Ator a : atoresAux) {
            atoresEmAtividade.add(new AtorEmAtividade(a.getId(),a.getNome(),a.getDataNascimento()));
        }

        if(atores.size()>0){
            return atoresEmAtividade;
        } else {
            throw new ListaVaziaException(TipoDominioException.ATOR.singular,TipoDominioException.ATOR.plural);
        }
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws ListaVaziaException, FiltroException {

        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atoresAux = atores.stream()
                .filter(a -> a.getStatusCarreira() == StatusCarreira.EM_ATIVIDADE)
                .filter(a -> a.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());

        List<AtorEmAtividade> atoresEmAtividade = new ArrayList<>();

        for (Ator a : atoresAux) {
            atoresEmAtividade.add(new AtorEmAtividade(a.getId(),a.getNome(),a.getDataNascimento()));
        }

        if(atores.size()>0){
            if (atoresEmAtividade.size()>0) {
                return atoresEmAtividade;
            } else {
                throw new FiltroException("Ator",filtroNome);
            }
        } else {
            throw new ListaVaziaException(TipoDominioException.ATOR.singular,TipoDominioException.ATOR.plural);
        }
    }

    public Ator consultarAtor(Integer id) throws IdException, CampoVazioException, ListaVaziaException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        List<Ator> atores = fakeDatabase.recuperaAtores();

        //Não foi pedido explicitamente para verificar este caso aqui neste método, mas acho válido.
        if(atores.isEmpty()){
            throw new ListaVaziaException(TipoDominioException.ATOR.singular,TipoDominioException.ATOR.plural);
        }

        List<Ator> atoresAux = atores.stream()
                .filter(a -> a.getId() == id)
                .collect(Collectors.toList());

        //Considerando que nao é necessário verificar por IDs repetidos.
        if(atoresAux.size() == 1) {
            return atoresAux.get(0);
        } else {
            throw new IdException("Ator",id);
        }
    }

    public List<Ator> consultarAtores() throws ListaVaziaException {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        if(atores.isEmpty()){
            throw new ListaVaziaException(TipoDominioException.ATOR.singular,TipoDominioException.ATOR.plural);
        } else {
            return atores;
        }
    }
}
