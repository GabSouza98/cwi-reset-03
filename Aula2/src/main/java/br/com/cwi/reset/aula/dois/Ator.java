package br.com.cwi.reset.aula.dois;

public class Ator extends Pessoa{

    private Integer numeroDeOscars;

    public Ator(String nome, Integer idade, Genero genero, Integer numeroDeOscars) {
        super(nome, idade, genero);
        this.numeroDeOscars = numeroDeOscars;
    }

//    public void imprimeCaracteristicas() {
//        super.imprimeCaracteristicas();
//        System.out.println("Número de Oscars: " + this.getNumeroDeOscars());
//    }

    public Integer getNumeroDeOscars() {
        return numeroDeOscars;
    }

}
