package br.com.cwi.reset.gabrielaraujodesouza.model;
import java.time.LocalDate;

public class Ator {

    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private Integer anoInicioAtividade;
    private StatusCarreira statusCarreira;

    public Ator(Integer id, String nome, LocalDate dataNascimento, Integer anoInicioAtividade, StatusCarreira statusCarreira) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
        this.statusCarreira = statusCarreira;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
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

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }

    public void setId(Integer id) {
        this.id = id;
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
