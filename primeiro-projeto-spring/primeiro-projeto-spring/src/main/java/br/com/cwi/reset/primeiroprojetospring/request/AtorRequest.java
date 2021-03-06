package br.com.cwi.reset.primeiroprojetospring.request;

import br.com.cwi.reset.primeiroprojetospring.domain.StatusCarreira;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class AtorRequest {

    private String nome;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Integer anoInicioAtividade;
    private StatusCarreira statusCarreira;

    public AtorRequest(String nome, LocalDate dataNascimento, Integer anoInicioAtividade, StatusCarreira statusCarreira) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
        this.statusCarreira = statusCarreira;
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

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }
}
