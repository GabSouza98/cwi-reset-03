package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.CampoVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.FiltroException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.IdException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.ListaVaziaException;
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

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws ListaVaziaException, FiltroException {

        final List<Ator> atoresCadastrados = fakeDatabase.recuperaAtores();

        if (atoresCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }

        final List<AtorEmAtividade> retorno = new ArrayList<>();

        if (filtroNome != null) {
            for (Ator ator : atoresCadastrados) {
                final boolean containsFilter = ator.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT));
                final boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if (containsFilter && emAtividade) {
                    retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        } else {
            for (Ator ator : atoresCadastrados) {
                final boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if (emAtividade) {
                    retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        }

        if (retorno.isEmpty()) {
            throw new FiltroException("Ator", filtroNome);
        }
        return retorno;
    }

    public Ator consultarAtor(Integer id) throws IdException, CampoVazioException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        List<Ator> atores = fakeDatabase.recuperaAtores();

        List<Ator> atoresAux = atores.stream()
                .filter(a -> a.getId() == id)
                .collect(Collectors.toList());

        if(atoresAux.size() == 1) {
            return atoresAux.get(0);
        } else {
            throw new IdException(TipoDominioException.ATOR.getSingular(),id);
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
