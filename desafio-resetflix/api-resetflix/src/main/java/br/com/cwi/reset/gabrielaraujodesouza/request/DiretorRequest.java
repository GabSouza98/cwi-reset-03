package br.com.cwi.reset.gabrielaraujodesouza.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DiretorRequest {

    private String nome;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Integer anoInicioAtividade;

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setAnoInicioAtividade(Integer anoInicioAtividade) {
        this.anoInicioAtividade = anoInicioAtividade;
    }
}
