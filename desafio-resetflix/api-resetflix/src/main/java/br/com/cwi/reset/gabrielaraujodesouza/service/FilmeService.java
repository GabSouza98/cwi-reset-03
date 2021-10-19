package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.model.Filme;
import br.com.cwi.reset.gabrielaraujodesouza.request.FilmeRequest;

public class FilmeService {

    private static Integer id = 1;
    private FakeDatabase fakeDatabase;

    public FilmeService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarFilme(FilmeRequest filmeRequest) {




    }

}
