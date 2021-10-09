package br.com.cwi.reset.aula.dois;

import java.time.LocalDate;

public class Diretor extends Pessoa {

        private int quantidadeFilmesDirigidos;

    public Diretor(String nome, LocalDate dataNascimento, Genero genero, int quantidadeFilmesDirigidos) {
        super(nome, dataNascimento, genero);
        this.quantidadeFilmesDirigidos = quantidadeFilmesDirigidos;
    }

    private int getQuantidadeFilmesDirigidos() {
        return quantidadeFilmesDirigidos;
    }
}
