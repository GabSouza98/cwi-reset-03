package br.com.cwi.reset.gabrielaraujodesouza.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class AtorEmAtividade {

    private Integer id;
    private String nome;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataNascimento;

    public AtorEmAtividade(Integer id, String nome, LocalDate dataNascimento) {
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
