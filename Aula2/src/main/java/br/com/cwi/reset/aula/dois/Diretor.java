package br.com.cwi.reset.aula.dois;

public class Diretor extends Pessoa {

        private int quantidadeFilmesDirigidos;

    public Diretor(String nome, Integer idade, Genero genero, int quantidadeFilmesDirigidos) {
        super(nome, idade, genero);
        this.quantidadeFilmesDirigidos = quantidadeFilmesDirigidos;
    }

    private int getQuantidadeFilmesDirigidos() {
        return quantidadeFilmesDirigidos;
    }
}
