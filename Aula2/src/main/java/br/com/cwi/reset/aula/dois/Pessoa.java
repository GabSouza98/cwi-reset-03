package br.com.cwi.reset.aula.dois;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;

    public Pessoa(String nome, LocalDate dataNascimento, Genero genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    public void imprimeCaracteristicas() {
        System.out.println("Nome: " + this.getNome());
        System.out.println("Idade: " + this.calcularIdade());
        System.out.println("Gênero: " + this.getGenero().getDescricao());
    }

    public String getNome() {
        return nome;
    }

    public Integer calcularIdade() {
        return Period.between(dataNascimento,LocalDate.now()).getYears();
    }

    public Genero getGenero() {
        return genero;
    }
}
