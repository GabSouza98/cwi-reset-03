package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.enums.TipoAtuacao;

public class PersonagemAtor {

    private Integer id;
    private Ator ator;
    private String nomePersonagem;
    private String descricaoPersonagem;
    private TipoAtuacao tipoAtuacao;

    public PersonagemAtor(Integer id, Ator ator, String nomePersonagem, String descricaoPersonagem, TipoAtuacao tipoAtuacao) {
        this.id = id;
        this.ator = ator;
        this.nomePersonagem = nomePersonagem;
        this.descricaoPersonagem = descricaoPersonagem;
        this.tipoAtuacao = tipoAtuacao;
    }

}
