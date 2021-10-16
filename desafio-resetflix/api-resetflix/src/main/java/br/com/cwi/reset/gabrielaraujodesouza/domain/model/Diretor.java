package br.com.cwi.reset.gabrielaraujodesouza.domain.model;

import java.time.LocalDate;

public class Diretor extends Funcionario {

    public Diretor(Integer id, String nome, LocalDate dataNascimento, Integer anoInicioAtividade) {
        super(id, nome, dataNascimento, anoInicioAtividade);
    }

}
