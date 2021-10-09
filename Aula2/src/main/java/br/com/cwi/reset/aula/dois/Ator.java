package br.com.cwi.reset.aula.dois;

public class Ator {

    private String nome;
    private Integer idade;
    private Integer numeroDeOscars;
    private Genero genero;

    public Ator(String nome, Integer idade, Integer numeroDeOscars, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.numeroDeOscars = numeroDeOscars;
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

    public Integer getIdade() {
        return idade;
    }

    public Integer getNumeroDeOscars() {
        return numeroDeOscars;
    }

    public Genero getGenero() {
        return genero;
    }
}
