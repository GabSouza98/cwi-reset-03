package br.com.cwi.reset.gabrielaraujodesouza.enums;

public enum TipoFuncionarios {
    DIRETOR("diretores","diretor"),
    ATOR("atores","ator");

    public final String plural;
    public final String singular;

    TipoFuncionarios(String plural, String singular) {
        this.plural = plural;
        this.singular = singular;
    }
}
