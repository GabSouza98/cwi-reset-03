package br.com.cwi.reset.gabrielaraujodesouza.request;

import br.com.cwi.reset.gabrielaraujodesouza.model.StatusCarreira;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class AtorRequest {

    private String nome;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Integer anoInicioAtividade;
    private StatusCarreira statusCarreira;

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
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

    public void setStatusCarreira(StatusCarreira statusCarreira) {
        this.statusCarreira = statusCarreira;
    }
}
