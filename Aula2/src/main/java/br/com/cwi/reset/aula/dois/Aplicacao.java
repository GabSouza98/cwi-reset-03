package br.com.cwi.reset.aula.dois;

public class Aplicacao {

    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Tarantino",58,50, Genero.MASCULINO);
        Diretor diretor2 = new Diretor("Spielberg",74,100, Genero.MASCULINO);

        Filme filme1 = new Filme("Pulp Fiction","Filme doido",120,1995,5,diretor1);
        Filme filme2 = new Filme("ET","Filme sobre a chegada de um ET na Terra",120,1982,5,diretor2);

        Ator ator1 = new Ator("Fulaninho", 30, 5, Genero.NAO_BINARIO);

        filme1.reproduzirFilme();
        filme2.reproduzirFilme();

        diretor1.imprimeCaracteristicas();
        ator1.imprimeCaracteristicas();
    }
}
