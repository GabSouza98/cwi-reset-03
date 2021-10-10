package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) {

        FakeDatabase fakeDatabase = new FakeDatabase(); //Guarda lista vazia de atores
        AtorService atorService = new AtorService(fakeDatabase);

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


        try {
            atorService.criarAtor(atorRequest);
            atorService.criarAtor(atorRequest2);
        } catch (NomeVazioException | DataNascimentoNula | StatusCarreiraNull
                | AnoInicioAtividadeNull | SemSobrenomeException
                | DataNascimentoMaiorQueAtualException
                | AnoInicioAtividadoAntesDeDataNascimentoException
                | AtorDuplicadoException e) {
            System.out.println(e.getMessage());
        }


        List<Ator> atores = fakeDatabase.recuperaAtores();

        //System.out.println("A lista contém " + atores.size());
        //System.out.println("Segundo ator: " + atores.get(0).getId());

        List<Ator> filtrada = atorService.listarAtoresEmAtividade("aaaa");

        for(Ator a : filtrada) {
            System.out.println(filtrada.get(filtrada.indexOf(a)).getNome());
        }

    }
}
