package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DiretorService {

    public static Integer id = 1;

    private FakeDatabase fakeDatabase;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, CampoVazioException, SemSobrenomeException, NomeDuplicadoException {

        if(diretorRequest.getNome().isEmpty()) {
            throw new CampoVazioException("Nome");
        }

        if(diretorRequest.getDataNascimento()==null){
            throw new CampoVazioException("DataNascimento");
        }

        if(diretorRequest.getAnoInicioAtividade()==null){
            throw new CampoVazioException("AnoInicioAtividade");
        }

        String[] palavras = diretorRequest.getNome().split("\\s+");
        if(palavras.length < 2) {
            throw new SemSobrenomeException("diretor");
        }

        if(ChronoUnit.DAYS.between(diretorRequest.getDataNascimento(), LocalDate.now()) < 0) {
            throw new DataNascimentoMaiorQueAtualException("diretores");
        }

        if(diretorRequest.getAnoInicioAtividade() - diretorRequest.getDataNascimento().getYear() < 0) {
            throw new AnoInicioAtividadoAntesDeDataNascimentoException("diretor");
        }

        for(Diretor d : fakeDatabase.recuperaDiretores()){
            if (d.getNome().equals(diretorRequest.getNome())) {
                throw new NomeDuplicadoException(diretorRequest.getNome());
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
            throw new ListaVaziaException("diretor","diretores");
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
