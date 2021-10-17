package br.com.cwi.reset.gabrielaraujodesouza.exception;

public enum TipoDominioException {
    DIRETOR("diretores","diretor"),
    ATOR("atores","ator"),
    ESTUDIO("estúdios","estúdio"),
    FILME("filmes","filme");

    public final String plural;
    public final String singular;

    TipoDominioException(String plural, String singular) {
        this.plural = plural;
        this.singular = singular;
    }

    public String getPlural() {
        return plural;
    }

    public String getSingular() {
        return singular;
    }
}

