package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AtorService {

    public static Integer id = 1;

    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) throws DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, CampoVazioException, SemSobrenomeException, NomeDuplicadoException {

        if(atorRequest.getNome().isEmpty()) {
            throw new CampoVazioException("Nome");
        }

        if(atorRequest.getDataNascimento()==null){
            throw new CampoVazioException("DataNascimento");
        }

        if(atorRequest.getStatusCarreira()==null){
            throw new CampoVazioException("StatusCarreira");
        }

        if(atorRequest.getAnoInicioAtividade()==null){
            throw new CampoVazioException("AnoInicioAtividade");
        }

        String[] palavras = atorRequest.getNome().split("\\s+");
        if(palavras.length < 2) {
            throw new SemSobrenomeException("ator");
        }

        if(ChronoUnit.DAYS.between(atorRequest.getDataNascimento(), LocalDate.now()) < 0) {
            throw new DataNascimentoMaiorQueAtualException("atores");
        }

        if(atorRequest.getAnoInicioAtividade() - atorRequest.getDataNascimento().getYear() < 0) {
            throw new AnoInicioAtividadoAntesDeDataNascimentoException("ator");
        }

        for(Ator a : fakeDatabase.recuperaAtores()){
            if (a.getNome().equals(atorRequest.getNome())) {
                throw new NomeDuplicadoException(atorRequest.getNome());
            }
        }

        Ator ator = new Ator(id++,
                    atorRequest.getNome(),
                    atorRequest.getDataNascimento(),
                    atorRequest.getStatusCarreira(),
                    atorRequest.getAnoInicioAtividade());
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
            throw new ListaVaziaException("ator","atores");
        } else {
            return atores;
        }
    }

}
