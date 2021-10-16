package br.com.cwi.reset.gabrielaraujodesouza.domain.model;

import br.com.cwi.reset.gabrielaraujodesouza.enums.StatusAtividade;

import java.time.LocalDate;

public class Estudio {

    private Integer id;
    private String nome;
    private String descricao;
    private LocalDate dataCriacao;
    private StatusAtividade statusAtividade;

    public Estudio(Integer id, String nome, String descricao, LocalDate dataCriacao, StatusAtividade statusAtividade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.statusAtividade = statusAtividade;
    }

}
