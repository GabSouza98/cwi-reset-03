package br.com.cwi.reset.gabrielaraujodesouza.domain;

import java.time.LocalDate;

public class AtorService {

    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public AtorService(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) {
    }

    public void criarAtor(AtorRequest atorRequest) {

    }

    // Demais métodos da classe
}
