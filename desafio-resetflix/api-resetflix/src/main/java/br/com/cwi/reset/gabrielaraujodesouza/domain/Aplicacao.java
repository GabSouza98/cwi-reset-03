package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) {

        FakeDatabase fakeDatabase = new FakeDatabase(); //Guarda lista vazia de atores
        AtorService atorService = new AtorService(fakeDatabase);
        DiretorService diretorService = new DiretorService(fakeDatabase);

        String nome = "Will Smith";
        LocalDate dataNascimento = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade = 1986;
        AtorRequest atorRequest = new AtorRequest(nome, dataNascimento, statusCarreira, anoInicioAtividade);

        String nome2 = "Will James";
        LocalDate dataNascimento2 = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira2 = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade2 = 1986;
        AtorRequest atorRequest2 = new AtorRequest(nome2, dataNascimento2, statusCarreira2, anoInicioAtividade2);

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

        } catch (NomeVazioException | DataNascimentoNula | StatusCarreiraNull
                | AnoInicioAtividadeNull | SemSobrenomeAtorException | SemSobrenomeDiretorException
                | DataNascimentoMaiorQueAtualException
                | AnoInicioAtividadoAntesDeDataNascimentoException
                | AtorDuplicadoException | DiretorDuplicadoException e) {
            System.out.println(e.getMessage());
        }

        //System.out.println("A lista contém " + atores.size());
        //System.out.println("Segundo ator: " + atores.get(0).getId());

        //Testa o método listarAtoresEmAtividade
//        List<Ator> filtrada = atorService.listarAtoresEmAtividade("aaaa");
//        for(Ator a : filtrada) {
//            System.out.println(filtrada.get(filtrada.indexOf(a)).getNome());
//        }

        //Testa o método consultarAtor
//        Ator atorProcurado = atorService.consultarAtor(3);
//        System.out.println(atorProcurado.getNome());

//        List<Ator> todosAtoresCadastrados = atorService.consultarAtores();
//        System.out.println(todosAtoresCadastrados.get(0).getNome());

//        List<Diretor> filtrada = diretorService.listarDiretores("Quen");
//        for(Diretor d : filtrada) {
//            System.out.println(filtrada.get(filtrada.indexOf(d)).getNome());
//        }

        //Testa o método consultarAtor
        try {
            Diretor diretorProcurado = diretorService.consultarDiretor(3);
            System.out.println(diretorProcurado.getNome());
        } catch (DiretorNaoEncontradoException e){
            System.out.println(e.getMessage());
        }


    }
}
