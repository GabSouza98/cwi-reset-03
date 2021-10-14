package br.com.cwi.reset.gabrielaraujodesouza.domain.request;

import java.time.LocalDate;

public class DiretorRequest extends FuncionarioRequest {

    public DiretorRequest(String nome, LocalDate dataNascimento, Integer anoInicioAtividade) {
        super(nome, dataNascimento, anoInicioAtividade);
    }

}
