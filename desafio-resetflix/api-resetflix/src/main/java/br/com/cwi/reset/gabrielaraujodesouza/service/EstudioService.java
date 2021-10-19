package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.CampoVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.FiltroException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.IdException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.ListaVaziaException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
import br.com.cwi.reset.gabrielaraujodesouza.request.EstudioRequest;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoEstudio;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class EstudioService {

    private static Integer id = 1;
    private FakeDatabase fakeDatabase;

    public EstudioService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarEstudio(EstudioRequest estudioRequest) throws Exception {

        new ValidacaoEstudio().accept(estudioRequest.getNome(),
                estudioRequest.getDescricao(),
                estudioRequest.getDataCriacao(),
                estudioRequest.getStatusAtividade(),
                TipoDominioException.ESTUDIO);

        Estudio estudio = new Estudio(id++,
                estudioRequest.getNome(),
                estudioRequest.getDescricao(),
                estudioRequest.getDataCriacao(),
                estudioRequest.getStatusAtividade());
        this.fakeDatabase.persisteEstudio(estudio);
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws ListaVaziaException, FiltroException {

        final List<Estudio> estudiosCadastrados = fakeDatabase.recuperaEstudios();

        if (estudiosCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ESTUDIO.getSingular(), TipoDominioException.ESTUDIO.getPlural());
        }

        final List<Estudio> retorno = new ArrayList<>();

        if(filtroNome!=null){
            for(Estudio estudio : estudiosCadastrados) {
                final boolean containsFilter = estudio.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT));
                if(containsFilter){
                    retorno.add(estudio);
                }
            }
        } else {
            return estudiosCadastrados;
        }

        if(retorno.size()<1){
            throw new FiltroException("Estúdio",filtroNome);
        }
        return retorno;
    }

    public Estudio consultarEstudio(Integer id) throws IdException, CampoVazioException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        List<Estudio> estudiosAux = estudios.stream()
                .filter(a -> a.getId() == id)
                .collect(Collectors.toList());

        if(estudiosAux.size() == 1) {
            return estudiosAux.get(0);
        } else {
            throw new IdException(TipoDominioException.ESTUDIO.getSingular(), id);
        }
    }


}
