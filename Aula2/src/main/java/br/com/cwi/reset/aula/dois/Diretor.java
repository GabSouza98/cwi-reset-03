package br.com.cwi.reset.aula.dois;

public class Diretor {

    private String nome;
    private int idade;
    private int quantidadeFilmesDirigidos;
    private Genero genero;

    public Diretor(String nome, int idade, int quantidadeFilmesDirigidos, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.quantidadeFilmesDirigidos = quantidadeFilmesDirigidos;
        this.genero = genero;
    }

    public void imprimeCaracteristicas() {
        System.out.println("Nome: " + this.getNome());
        System.out.println("Idade: " + this.getIdade());
        System.out.println("Gênero: " + this.genero.getDescricao());
    }

    public String getNome() {
        return nome;
    }
    private int getIdade() {
        return idade;
    }
    private int getQuantidadeFilmesDirigidos() {
        return quantidadeFilmesDirigidos;
    }
}
