package br.com.cwi.reset.gabrielaraujodesouza.domain;

import java.time.LocalDate;

public abstract class FuncionarioRequest {

    private String nome;
    private LocalDate dataNascimento;
    private Integer anoInicioAtividade;

    public FuncionarioRequest(String nome, LocalDate dataNascimento, Integer anoInicioAtividade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }
}
