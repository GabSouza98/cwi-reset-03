package br.com.cwi.reset.gabrielaraujodesouza.domain;

import java.time.LocalDate;

public class AtoresEmAtividade {

    private Integer id;
    private String nome;
    private LocalDate dataNascimento;

    public AtoresEmAtividade(Integer id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

}
