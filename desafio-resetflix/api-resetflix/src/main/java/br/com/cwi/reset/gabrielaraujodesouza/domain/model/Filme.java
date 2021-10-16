package br.com.cwi.reset.gabrielaraujodesouza.domain.model;

import br.com.cwi.reset.gabrielaraujodesouza.enums.Genero;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class Filme {

    private Integer id;
    private String nome;
    private LocalDate anoLancamento;
    private Image capaFilme; //que diabo é isso? Uma imagem?
    private List<Genero> generos;
    private Diretor diretor;
    private List<PersonagemAtor> personagens;
    private String resumo;

    public Filme(Integer id, String nome, LocalDate anoLancamento, Image capaFilme, List<Genero> generos, Diretor diretor, List<PersonagemAtor> personagens, String resumo) {
        this.id = id;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.generos = generos;
        this.diretor = diretor;
        this.personagens = personagens;
        this.resumo = resumo;
    }
}
