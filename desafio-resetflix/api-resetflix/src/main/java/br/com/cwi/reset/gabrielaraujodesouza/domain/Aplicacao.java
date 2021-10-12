package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.enums.StatusCarreira;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import java.time.LocalDate;
import java.time.Month;

public class Aplicacao {

    public static void main(String[] args) {

        FakeDatabase fakeDatabase = new FakeDatabase(); //Guarda lista vazia de atores
        AtorService atorService = new AtorService(fakeDatabase);
        DiretorService diretorService = new DiretorService(fakeDatabase);

        String nome = "Will Smith";
        LocalDate dataNascimento = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade = 1986;
        AtorRequest atorRequest = new AtorRequest(nome, dataNascimento, anoInicioAtividade, statusCarreira);

        String nome2 = "Will James";
        LocalDate dataNascimento2 = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira2 = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade2 = 1985;
        AtorRequest atorRequest2 = new AtorRequest(nome2, dataNascimento2, anoInicioAtividade2, statusCarreira2);

        String nome3 = "Tim Burton";
        LocalDate dataNascimento3 = LocalDate.of(1970, Month.OCTOBER, 30);
        Integer anoInicioAtividade3 = 1985;
        DiretorRequest diretorRequest = new DiretorRequest(nome3, dataNascimento3, anoInicioAtividade3);

        String nome4 = "Quentin Tarantino";
        LocalDate dataNascimento4 = LocalDate.of(1970, Month.OCTOBER, 30);
        Integer anoInicioAtividade4 = 1985;
        DiretorRequest diretorRequest2 = new DiretorRequest(nome4, dataNascimento4, anoInicioAtividade4);

        try {
            atorService.criarAtor(atorRequest);
            atorService.criarAtor(atorRequest2);
            diretorService.cadastrarDiretor(diretorRequest);
            diretorService.cadastrarDiretor(diretorRequest2);
        } catch ( CampoVazioException
                | SemSobrenomeException
                | DataNascimentoMaiorQueAtualException
                | AnoInicioAtividadoAntesDeDataNascimentoException
                | NomeDuplicadoException e) {
            System.out.println(e.getMessage());
        }

        //System.out.println("A lista contém " + atores.size());
        //System.out.println("Segundo ator: " + atores.get(0).getId());

        //Testa o método listarAtoresEmAtividade com filtro
//        try {
//            List<Ator> filtrada = atorService.listarAtoresEmAtividade("James");
//            for(Ator a : filtrada) {
//                System.out.println(filtrada.get(filtrada.indexOf(a)).getNome());
//            }
//        } catch (FiltroException | ListaVaziaException e) {
//            System.out.println(e.getMessage());
//        }

        //Testa o método listarAtoresEmAtividade sem filtro
//        try {
//            List<Ator> filtrada = atorService.listarAtoresEmAtividade();
//            for(Ator a : filtrada) {
//                System.out.println(filtrada.get(filtrada.indexOf(a)).getNome());
//            }
//        } catch ( ListaVaziaException e) {
//            System.out.println(e.getMessage());
//        }

        //Testa o método consultarAtor
//        try {
//            Ator atorProcurado = atorService.consultarAtor(null);
//            System.out.println(atorProcurado.getNome());
//        } catch (IdException | CampoVazioException e) {
//            System.out.println(e.getMessage());
//        }

        //Testa o método consultarAtores
//        try {
//            List<Ator> todosAtoresCadastrados = atorService.consultarAtores();
//            System.out.println(todosAtoresCadastrados.get(1).getNome());
//        } catch (ListaVaziaException e) {
//            System.out.println(e.getMessage());
//        }


        //Testa método listarDiretores com filtro
//        try {
//            List<Diretor> filtrada = diretorService.listarDiretores("Tim");
//            for(Diretor d : filtrada) {
//                System.out.println(filtrada.get(filtrada.indexOf(d)).getNome());
//            }
//        } catch (FiltroException | ListaVaziaException e) {
//            System.out.println(e.getMessage());
//        }

        //Testa o método listarDiretores sem filtro
//        try {
//            List<Diretor> filtrada = diretorService.listarDiretores();
//            for(Diretor d : filtrada) {
//                System.out.println(filtrada.get(filtrada.indexOf(d)).getNome());
//            }
//        } catch ( ListaVaziaException e) {
//            System.out.println(e.getMessage());
//        }

//        //Testa o método consultarDiretor
//        try {
//            Diretor diretorProcurado = diretorService.consultarDiretor(3);
//            System.out.println(diretorProcurado.getNome());
//        } catch (IdException | CampoVazioException e){
//            System.out.println(e.getMessage());
//        }


    }
}
