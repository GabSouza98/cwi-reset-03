package br.com.cwi.reset.gabrielaraujodesouza.request;

import br.com.cwi.reset.gabrielaraujodesouza.model.StatusCarreira;

import java.time.LocalDate;

public class AtorRequest extends FuncionarioRequest {

    private StatusCarreira statusCarreira;

    public AtorRequest(String nome, LocalDate dataNascimento, Integer anoInicioAtividade, StatusCarreira statusCarreira) {
        super(nome, dataNascimento, anoInicioAtividade);
        this.statusCarreira = statusCarreira;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }

}
