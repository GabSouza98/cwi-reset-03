package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AtorService {

    public static Integer id = 1;

    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) throws NomeVazioException, DataNascimentoNula, StatusCarreiraNull, AnoInicioAtividadeNull, SemSobrenomeException, DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, AtorDuplicadoException {

        if(atorRequest.getNome().isEmpty()) {
            throw new NomeVazioException();
        }

        if(atorRequest.getDataNascimento()==null){
            throw new DataNascimentoNula();
        }

        if(atorRequest.getStatusCarreira()==null){
            throw new StatusCarreiraNull();
        }

        if(atorRequest.getAnoInicioAtividade()==null){
            throw new AnoInicioAtividadeNull();
        }


        String[] palavras = atorRequest.getNome().split("\\s+");
        if(palavras.length < 2) {
            throw new SemSobrenomeException();
        }

        if(ChronoUnit.DAYS.between(atorRequest.getDataNascimento(), LocalDate.now()) < 0) {
            throw new DataNascimentoMaiorQueAtualException();
        }

        if(atorRequest.getAnoInicioAtividade() - atorRequest.getDataNascimento().getYear() < 0) {
            throw new AnoInicioAtividadoAntesDeDataNascimentoException();
        }

        for(Ator a : fakeDatabase.recuperaAtores()){
            if (a.getNome().equals(atorRequest.getNome())) {
                System.out.println("Já existe um ator cadastrado para o nome " + atorRequest.getNome() +".");
                throw new AtorDuplicadoException();
            }
        }

        Ator ator = new Ator(id++,
                    atorRequest.getNome(),
                    atorRequest.getDataNascimento(),
                    atorRequest.getStatusCarreira(),
                    atorRequest.getAnoInicioAtividade());
        this.fakeDatabase.persisteAtor(ator);
    }

    public List listarAtoresEmAtividade() {

        List<Ator> atoresAux = new ArrayList<>();
        for(Ator a : fakeDatabase.recuperaAtores()) {
            if (a.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                atoresAux.add(a);
            }
        }
        return atoresAux;
    }

    public List listarAtoresEmAtividade(String parteDoNome) {

        List<Ator> atoresAux = new ArrayList<>();
        for(Ator a : fakeDatabase.recuperaAtores()) {
            if (a.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                if (a.getNome().contains(parteDoNome)) {
                    atoresAux.add(a);
                }
            }
        }
        return atoresAux;
    }


}
