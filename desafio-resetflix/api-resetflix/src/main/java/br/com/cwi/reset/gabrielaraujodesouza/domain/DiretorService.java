package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.enums.TipoFuncionarios;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DiretorService extends FuncionarioService{

    public static Integer id = 1;

    public DiretorService(FakeDatabase fakeDatabase) {
        super(fakeDatabase);
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, CampoVazioException, SemSobrenomeException, NomeDuplicadoException {

        super.cadastrarFuncionario(diretorRequest);

        for(Diretor d : fakeDatabase.recuperaDiretores()){
            if (d.getNome().equals(diretorRequest.getNome())) {
                throw new NomeDuplicadoException(this.getClass().toString().toLowerCase(), diretorRequest.getNome());
            }
        }

        Diretor diretor = new Diretor(id++,
                diretorRequest.getNome(),
                diretorRequest.getDataNascimento(),
                diretorRequest.getAnoInicioAtividade());
        this.fakeDatabase.persisteDiretor(diretor);
    }

    public List listarDiretores() throws ListaVaziaException {

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        List<Diretor> diretoresAux = new ArrayList<>();

        if(diretores.size()>0){
            for(Diretor d : diretores) {
                diretoresAux.add(d);
            }
            return diretoresAux;
        } else {
            throw new ListaVaziaException(TipoFuncionarios.DIRETOR.singular,TipoFuncionarios.DIRETOR.plural);
        }
    }

    public List listarDiretores(String filtroNome) throws ListaVaziaException, FiltroException {

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        List<Diretor> diretoresAux = new ArrayList<>();

        if(diretores.size()>0){
            for(Diretor d : diretores) {
                if (d.getNome().contains(filtroNome)) {
                    diretoresAux.add(d);
                }
            }
            if (diretoresAux.size()==0) {
                throw new FiltroException("Diretor",filtroNome);
            }
        } else {
            throw new ListaVaziaException("diretor","diretores");
        }
        return diretoresAux;
    }

    public Diretor consultarDiretor(Integer id) throws IdException, CampoVazioException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        boolean diretorEncontrado = false;
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        Diretor diretorProcurado = null;

        for(Diretor d : diretores) {
            if(d.getId() == id){
                diretorEncontrado = true;
                diretorProcurado = d;
                break;
            }
        }
        if (!diretorEncontrado) {
            throw new IdException("Diretor",id);
        } else {
            return diretorProcurado;
        }
    }

}
