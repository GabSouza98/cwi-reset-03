package br.com.cwi.reset.gabrielaraujodesouza.request;

import br.com.cwi.reset.gabrielaraujodesouza.model.StatusCarreira;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class AtorRequest {

    private String nome;

    @JsonFormat(pattern="dd/MM/yyyy")
    private String dataNascimento;

    private Integer anoInicioAtividade;
    private StatusCarreira statusCarreira;

    public AtorRequest(String nome, String dataNascimento, Integer anoInicioAtividade, StatusCarreira statusCarreira) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
        this.statusCarreira = statusCarreira;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }
}
