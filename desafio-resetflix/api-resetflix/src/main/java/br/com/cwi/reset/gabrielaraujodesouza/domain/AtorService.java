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

    public void criarAtor(AtorRequest atorRequest) throws NomeVazioException, DataNascimentoNula, StatusCarreiraNull, AnoInicioAtividadeNull, SemSobrenomeAtorException, DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, AtorDuplicadoException {

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
            throw new SemSobrenomeAtorException();
        }

        if(ChronoUnit.DAYS.between(atorRequest.getDataNascimento(), LocalDate.now()) < 0) {
            System.out.println("Não é possível cadastrar atores não nascidos.");
            throw new DataNascimentoMaiorQueAtualException();
        }

        if(atorRequest.getAnoInicioAtividade() - atorRequest.getDataNascimento().getYear() < 0) {
            System.out.println("Ano de início de atividade inválido para o ator cadastrado.");
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

        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atoresAux = new ArrayList<>();

        if(atores.size()>0) {
            for(Ator a : atores) {
                if (a.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                    atoresAux.add(a);
                }
            }
        }
        else {
            System.out.println("Nenhum ator cadastrado, favor cadastar atores.");
        }
        return atoresAux;
    }

    public List listarAtoresEmAtividade(String parteDoNome) {

        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atoresAux = new ArrayList<>();

        if(atores.size()>0){
            for(Ator a : atores) {
                if (a.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                    if (a.getNome().contains(parteDoNome)) {
                        atoresAux.add(a);
                    }
                }
            }
            if (atoresAux.size()==0) {
                System.out.println(String.format("Ator não encontrato com o filtro %s, favor informar outro filtro.", parteDoNome));
            }
        } else {
            System.out.println("Nenhum ator cadastrado, favor cadastar atores.");
        }
        return atoresAux;
    }

    public Ator consultarAtor(Integer id) {

        boolean atorEncontrado = false;
        List<Ator> atores = fakeDatabase.recuperaAtores();
        Ator atorProcurado = null;

        for(Ator a : atores) {
            if(a.getId() == id){
                atorEncontrado = true;
                atorProcurado = a;
            }
        }
        if (!atorEncontrado) {
            System.out.println(String.format("Nenhum ator encontrado com o parâmetro id=%d, favor verifique os parâmetros informados.", id));
            atorProcurado = new Ator(0,"teste",LocalDate.now(),StatusCarreira.APOSENTADO,2021);
        }
        return atorProcurado;
    }

    public List consultarAtores() {

        List<Ator> atores = fakeDatabase.recuperaAtores();
        if(atores.size()==0){
            System.out.println("Nenhum ator cadastrado, favor cadastar atores.");
        }

        return atores;
    }

}
