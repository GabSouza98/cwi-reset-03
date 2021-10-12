package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.enums.StatusCarreira;
import br.com.cwi.reset.gabrielaraujodesouza.enums.TipoFuncionarios;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AtorService extends FuncionarioService{

    public static Integer id = 1;

    public AtorService(FakeDatabase fakeDatabase) {
        super(fakeDatabase);
    }

    public void criarAtor(AtorRequest atorRequest) throws DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, CampoVazioException, SemSobrenomeException, NomeDuplicadoException {

        super.cadastrarFuncionario(atorRequest);

        if(atorRequest.getStatusCarreira()==null){
            throw new CampoVazioException("StatusCarreira");
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

    public List listarAtoresEmAtividade() throws ListaVaziaException {

        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atoresAux = new ArrayList<>();

        if(atores.size()>0) {
            for(Ator a : atores) {
                if (a.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                    atoresAux.add(a);
                }
            }
        } else {
            throw new ListaVaziaException("ator","atores");
        }
        return atoresAux;
    }

    public List listarAtoresEmAtividade(String filtroNome) throws ListaVaziaException, FiltroException {

        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atoresAux = new ArrayList<>();

        if(atores.size()>0){
            for(Ator a : atores) {
                if (a.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                    if (a.getNome().contains(filtroNome)) {
                        atoresAux.add(a);
                    }
                }
            }
            if (atoresAux.size()==0) {
                throw new FiltroException("Ator",filtroNome);
            }
        } else {
            throw new ListaVaziaException("ator","atores");
        }
        return atoresAux;
    }

//    public List listarAtoresEmAtividade(String filtroNome){
//
//        List<Ator> atores = fakeDatabase.recuperaAtores();
//
//
//        List<Ator> atoresAux = atores.stream()
//                .filter(a -> a.getStatusCarreira() == StatusCarreira.EM_ATIVIDADE)
//                .filter(a -> a.getNome().contains(filtroNome))
//                .collect(Collectors.toList());
//        return atoresAux;
//    }

    public Ator consultarAtor(Integer id) throws IdException, CampoVazioException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        boolean atorEncontrado = false;
        List<Ator> atores = fakeDatabase.recuperaAtores();
        Ator atorProcurado = null;

        for(Ator a : atores) {
            if(a.getId() == id){
                atorEncontrado = true;
                atorProcurado = a;
                break;
            }
        }
        if (!atorEncontrado) {
            throw new IdException("Ator",id);
        } else {
            return atorProcurado;
        }
    }

    public List consultarAtores() throws ListaVaziaException {

        List<Ator> atores = fakeDatabase.recuperaAtores();
        if(atores.size()==0){
            throw new ListaVaziaException(TipoFuncionarios.ATOR.singular,TipoFuncionarios.ATOR.plural);
        } else {
            return atores;
        }
    }

}
