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

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws NomeVazioException, DataNascimentoNula, AnoInicioAtividadeNull, DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, DiretorDuplicadoException, SemSobrenomeDiretorException {

        if(diretorRequest.getNome().isEmpty()) {
            throw new NomeVazioException();
        }

        if(diretorRequest.getDataNascimento()==null){
            throw new DataNascimentoNula();
        }

        if(diretorRequest.getAnoInicioAtividade()==null){
            throw new AnoInicioAtividadeNull();
        }

        String[] palavras = diretorRequest.getNome().split("\\s+");
        if(palavras.length < 2) {
            throw new SemSobrenomeDiretorException();
        }

        if(ChronoUnit.DAYS.between(diretorRequest.getDataNascimento(), LocalDate.now()) < 0) {
            System.out.println("Não é possível cadastrar diretores não nascidos.");
            throw new DataNascimentoMaiorQueAtualException();
        }

        if(diretorRequest.getAnoInicioAtividade() - diretorRequest.getDataNascimento().getYear() < 0) {
            System.out.println("Ano de início de atividade inválido para o diretor cadastrado.");
            throw new AnoInicioAtividadoAntesDeDataNascimentoException();
        }

        for(Diretor d : fakeDatabase.recuperaDiretores()){
            if (d.getNome().equals(diretorRequest.getNome())) {
                System.out.println("Já existe um diretor cadastrado para o nome " + diretorRequest.getNome() +".");
                throw new DiretorDuplicadoException();
            }
        }

        Diretor diretor = new Diretor(id++,
                diretorRequest.getNome(),
                diretorRequest.getDataNascimento(),
                diretorRequest.getAnoInicioAtividade());
        this.fakeDatabase.persisteDiretor(diretor);
    }

    public List listarDiretores() {

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        List<Diretor> diretoresAux = new ArrayList<>();

        if(diretores.size()>0){
            for(Diretor d : diretores) {
                diretoresAux.add(d);
            }
        } else {
            System.out.println("Nenhum diretor cadastrado, favor cadastar diretores.");
        }
        return diretoresAux;
    }

    public List listarDiretores(String filtroNome) {

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        List<Diretor> diretoresAux = new ArrayList<>();

        if(diretores.size()>0){
            for(Diretor d : diretores) {
                if (d.getNome().contains(filtroNome)) {
                    diretoresAux.add(d);
                }
            }
            if (diretoresAux.size()==0) {
                System.out.println(String.format("Diretor não encontrato com o filtro %s, favor informar outro filtro.", filtroNome));
            }
        } else {
            System.out.println("Nenhum diretor cadastrado, favor cadastar diretores.");
        }
        return diretoresAux;
    }

    public Diretor consultarDiretor(Integer id) {

        boolean diretorEncontrado = false;
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        Diretor diretorProcurado = null;

        for(Diretor d : diretores) {
            if(d.getId() == id){
                diretorEncontrado = true;
                diretorProcurado = d;
            }
        }

        if (!diretorEncontrado) {
            System.out.println(String.format("Nenhum diretor encontrado com o parâmetro id=%d, favor verifique os parâmetros informados.", id));
            diretorProcurado = new Diretor(0,"teste",LocalDate.now(),2021);
        }
        return diretorProcurado;
    } 

}
