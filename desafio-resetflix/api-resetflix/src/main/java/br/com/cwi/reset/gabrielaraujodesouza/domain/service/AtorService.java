package br.com.cwi.reset.gabrielaraujodesouza.domain.service;

import br.com.cwi.reset.gabrielaraujodesouza.domain.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.domain.AtoresEmAtividade;
import br.com.cwi.reset.gabrielaraujodesouza.domain.request.AtorRequest;
import br.com.cwi.reset.gabrielaraujodesouza.domain.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.enums.StatusCarreira;
import br.com.cwi.reset.gabrielaraujodesouza.enums.TipoFuncionarios;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AtorService extends FuncionarioService {

    private static Integer id = 1;

    public AtorService(FakeDatabase fakeDatabase) {
        super(fakeDatabase);
    }

    public void criarAtor(AtorRequest atorRequest) throws DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, CampoVazioException, SemSobrenomeException, NomeDuplicadoException {

        super.verificarDados(atorRequest);

        if(atorRequest.getStatusCarreira()==null){
            throw new StatusCarreiraVazioException();
        }

        for(Ator a : fakeDatabase.recuperaAtores()){
            if (a.getNome().equals(atorRequest.getNome())) {
                throw new NomeDuplicadoException(TipoFuncionarios.ATOR.singular, atorRequest.getNome());
            }
        }

        Ator ator = new Ator(id++,
                    atorRequest.getNome(),
                    atorRequest.getDataNascimento(),
                    atorRequest.getAnoInicioAtividade(),
                    atorRequest.getStatusCarreira());
        this.fakeDatabase.persisteAtor(ator);
    }

    public List<AtoresEmAtividade> listarAtoresEmAtividade() throws ListaVaziaException {

        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atoresAux = atores.stream()
                .filter(a -> a.getStatusCarreira() == StatusCarreira.EM_ATIVIDADE)
                .collect(Collectors.toList());

        List<AtoresEmAtividade> atoresEmAtividade = new ArrayList<>();

        for (Ator a : atoresAux) {
            atoresEmAtividade.add(new AtoresEmAtividade(a.getId(),a.getNome(),a.getDataNascimento()));
        }

        if(atores.size()>0){
            return atoresEmAtividade;
        } else {
            throw new ListaVaziaException(TipoFuncionarios.ATOR.singular,TipoFuncionarios.ATOR.plural);
        }
    }

    public List<AtoresEmAtividade> listarAtoresEmAtividade(String filtroNome) throws ListaVaziaException, FiltroException {

        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atoresAux = atores.stream()
                .filter(a -> a.getStatusCarreira() == StatusCarreira.EM_ATIVIDADE)
                .filter(a -> a.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());

        List<AtoresEmAtividade> atoresEmAtividade = new ArrayList<>();

        for (Ator a : atoresAux) {
            atoresEmAtividade.add(new AtoresEmAtividade(a.getId(),a.getNome(),a.getDataNascimento()));
        }

        if(atores.size()>0){
            if (atoresEmAtividade.size()>0) {
                return atoresEmAtividade;
            } else {
                throw new FiltroException("Ator",filtroNome);
            }
        } else {
            throw new ListaVaziaException(TipoFuncionarios.ATOR.singular,TipoFuncionarios.ATOR.plural);
        }
    }

    public Ator consultarAtor(Integer id) throws IdException, CampoVazioException, ListaVaziaException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        List<Ator> atores = fakeDatabase.recuperaAtores();

        //Não foi pedido explicitamente para verificar este caso aqui neste método, mas acho válido.
        if(atores.isEmpty()){
            throw new ListaVaziaException(TipoFuncionarios.ATOR.singular,TipoFuncionarios.ATOR.plural);
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
            throw new ListaVaziaException(TipoFuncionarios.ATOR.singular,TipoFuncionarios.ATOR.plural);
        } else {
            return atores;
        }
    }
}
