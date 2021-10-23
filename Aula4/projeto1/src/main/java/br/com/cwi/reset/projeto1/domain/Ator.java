package br.com.cwi.reset.projeto1.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ator")
public class Ator extends Pessoa {

    private Integer numeroOscars;

    public Ator(String nome, LocalDate dataNascimento, Genero genero, Integer numeroOscars) {
        super(nome, dataNascimento, genero);
        this.numeroOscars = numeroOscars;
    }

    public Integer getNumeroOscars() {
        return numeroOscars;
    }

    public void setNumeroOscars(Integer numeroOscars) {
        this.numeroOscars = numeroOscars;
    }

    public Ator() {

    }

}
