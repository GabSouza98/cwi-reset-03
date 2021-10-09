package br.com.cwi.reset.aula.dois;

public class Filme {

    private String nome;
    private String descricao;
    private int duracao;
    private int anoDeLancamento;
    private int avaliacao;
    private Diretor diretor;

    public Filme(String nome, String descricao, int duracao, int anoDeLancamento, int avaliacao, Diretor diretor) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoDeLancamento = anoDeLancamento;
        this.avaliacao = avaliacao;
        this.diretor = diretor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public void reproduzirFilme() {
        System.out.println("Nome do filme: " + this.getNome());
        System.out.println("Descrição: " + this.getDescricao());
        System.out.println("Duração: " + this.getDuracao());
        System.out.println("Nome do diretor: " + this.getDiretor().getNome());
    }



}
