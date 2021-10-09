package br.com.cwi.reset.aula.dois;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aplicacao {

    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Tarantino", LocalDate.parse("1998-01-30", DateTimeFormatter.ofPattern("yyyy-MM-dd")), Genero.MASCULINO, 30);
        Diretor diretor2 = new Diretor("Spielberg", LocalDate.parse("1996-01-30", DateTimeFormatter.ofPattern("yyyy-MM-dd")), Genero.MASCULINO, 10);

        Ator ator1 = new Ator("Fulaninho", LocalDate.parse("1990-01-30", DateTimeFormatter.ofPattern("yyyy-MM-dd")), Genero.NAO_BINARIO, 5);


        try {
            Filme filme1 = new Filme("Pulp Fiction","Filme doido",120,1995,5,diretor1);
            Filme filme2 = new Filme("ET","Filme sobre a chegada de um ET na Terra",120,1982,5,diretor2);
        }
        catch (AvaliacaoForaDoPadraoException e) {
            System.out.println(e.getMessage());
        }


        //filme1.reproduzirFilme();
        //filme2.reproduzirFilme();

        //diretor2.imprimeCaracteristicas();
        ator1.imprimeCaracteristicas();
    }
}
