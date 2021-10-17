package br.com.cwi.reset.gabrielaraujodesouza.model;

import java.time.LocalDate;

public class Ator extends Funcionario {

    private StatusCarreira statusCarreira;

    public Ator(Integer id, String nome, LocalDate dataNascimento, Integer anoInicioAtividade, StatusCarreira statusCarreira) {
        super(id, nome, dataNascimento, anoInicioAtividade);
        this.statusCarreira = statusCarreira;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }





}
