package br.com.cwi.reset.gabrielaraujodesouza.domain.service;

import br.com.cwi.reset.gabrielaraujodesouza.domain.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.domain.request.DiretorRequest;
import br.com.cwi.reset.gabrielaraujodesouza.domain.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.enums.TipoFuncionarios;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import java.util.List;
import java.util.stream.Collectors;

public class DiretorService extends FuncionarioService {

    private static Integer id = 1;

    public DiretorService(FakeDatabase fakeDatabase) {
        super(fakeDatabase);
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, CampoVazioException, SemSobrenomeException, NomeDuplicadoException {

        super.verificarDados(diretorRequest);

        for(Diretor d : fakeDatabase.recuperaDiretores()){
            if (d.getNome().equals(diretorRequest.getNome())) {
                throw new NomeDuplicadoException(TipoFuncionarios.DIRETOR.singular, diretorRequest.getNome());
            }
        }

        Diretor diretor = new Diretor(id++,
                diretorRequest.getNome(),
                diretorRequest.getDataNascimento(),
                diretorRequest.getAnoInicioAtividade());
        this.fakeDatabase.persisteDiretor(diretor);
    }

    public List<Diretor> listarDiretores() throws ListaVaziaException {

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        if(diretores.size()==0){
            throw new ListaVaziaException(TipoFuncionarios.DIRETOR.singular,TipoFuncionarios.DIRETOR.plural);
        } else {
            return diretores;
        }
    }

    public List<Diretor> listarDiretores(String filtroNome) throws ListaVaziaException, FiltroException {

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        List<Diretor> diretoresAux = diretores.stream()
                .filter(d -> d.getNome().contains(filtroNome))
                .collect(Collectors.toList());

        if(diretores.size()==0){
            throw new ListaVaziaException(TipoFuncionarios.DIRETOR.singular, TipoFuncionarios.DIRETOR.plural);
        }
        if(diretoresAux.size()==0) {
            throw new FiltroException("Diretor", filtroNome);
        }

        return diretoresAux;
    }

    public Diretor consultarDiretor(Integer id) throws IdException, CampoVazioException, ListaVaziaException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();

        //Não foi pedido explicitamente para verificar este caso aqui neste método, mas acho válido.
        if(diretores.isEmpty()){
            throw new ListaVaziaException(TipoFuncionarios.DIRETOR.singular,TipoFuncionarios.DIRETOR.plural);
        }

        List<Diretor> diretoresAux = diretores.stream()
                .filter(a -> a.getId() == id)
                .collect(Collectors.toList());

        //Considerando que nao é necessário verificar por IDs repetidos.
        if(diretoresAux.size() == 1) {
            return diretoresAux.get(0);
        } else {
            throw new IdException("Diretor",id);
        }
    }
}
