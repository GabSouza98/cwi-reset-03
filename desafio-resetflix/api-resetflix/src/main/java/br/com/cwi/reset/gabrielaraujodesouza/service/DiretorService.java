package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.request.DiretorRequest;
import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.response.AtorEmAtividade;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoDiretor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DiretorService {

    private static Integer id = 1;
    private FakeDatabase fakeDatabase;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws Exception {

        new ValidacaoDiretor().accept(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade(), TipoDominioException.DIRETOR);

        Diretor diretor = new Diretor(id++,
                diretorRequest.getNome(),
                diretorRequest.getDataNascimento(),
                diretorRequest.getAnoInicioAtividade());
        this.fakeDatabase.persisteDiretor(diretor);
    }

    public List<Diretor> listarDiretores(String filtroNome) throws ListaVaziaException, FiltroException {

        final List<Diretor> diretoresCadastrados = fakeDatabase.recuperaDiretores();

        if (diretoresCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }

        final List<Diretor> retorno = new ArrayList<>();

        if(filtroNome!=null){
            for(Diretor diretor : diretoresCadastrados) {
                final boolean containsFilter = diretor.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT));
                if(containsFilter){
                    retorno.add(diretor);
                }
            }
        } else {
            return diretoresCadastrados;
        }

        if(retorno.size()<1){
            throw new FiltroException("Diretor",filtroNome);
        }
        return retorno;
    }

    public Diretor consultarDiretor(Integer id) throws IdException, CampoVazioException, ListaVaziaException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        List<Diretor> diretoresAux = diretores.stream()
                .filter(a -> a.getId() == id)
                .collect(Collectors.toList());

        if(diretoresAux.size() == 1) {
            return diretoresAux.get(0);
        } else {
            throw new IdException(TipoDominioException.DIRETOR.getSingular(), id);
        }
    }
}
