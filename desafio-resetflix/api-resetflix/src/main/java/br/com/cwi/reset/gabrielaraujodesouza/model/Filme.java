package br.com.cwi.reset.gabrielaraujodesouza.model;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class Filme {

    private Integer id;
    private String nome;
    private Integer anoLancamento;
    private String capaFilme; //que diabo é isso? Uma imagem?
    private List<Genero> generos;
    private Diretor diretor;
    private Estudio estudio;
    private String resumo;
    private List<PersonagemAtor> personagens;

    public Filme(Integer id, String nome, Integer anoLancamento, String capaFilme, List<Genero> generos, Diretor diretor, Estudio estudio, String resumo, List<PersonagemAtor> personagens) {
        this.id = id;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.generos = generos;
        this.diretor = diretor;
        this.estudio = estudio;
        this.resumo = resumo;
        this.personagens = personagens;
    }
}
