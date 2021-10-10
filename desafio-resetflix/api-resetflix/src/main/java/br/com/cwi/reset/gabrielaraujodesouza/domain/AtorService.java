package br.com.cwi.reset.gabrielaraujodesouza.domain;

import java.time.LocalDate;

public class AtorService {

    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) {

        Ator ator = new Ator(atorRequest.getNome(),atorRequest.getDataNascimento(),atorRequest.getStatusCarreira(),atorRequest.getAnoInicioAtividade());
        this.fakeDatabase.persisteAtor(ator);


    }

    // Demais métodos da classe
}
