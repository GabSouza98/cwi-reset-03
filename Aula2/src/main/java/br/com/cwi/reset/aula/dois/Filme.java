package br.com.cwi.reset.aula.dois;

public class Filme {

    private String nome;
    private String descricao;
    private int duracao;
    private int anoDeLancamento;
    private int avaliacao;
    private Diretor diretor;

    public Filme(String nome, String descricao, int duracao, int anoDeLancamento, int avaliacao, Diretor diretor) throws AvaliacaoForaDoPadraoException{
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoDeLancamento = anoDeLancamento;
        this.avaliacao = getAvaliacao(avaliacao);
        this.diretor = diretor;
    }

    private int getAvaliacao(int avaliacao) throws AvaliacaoForaDoPadraoException {

        if(avaliacao>5 || avaliacao<1) {
            throw new AvaliacaoForaDoPadraoException();
        }
        return avaliacao;
    }

    public void reproduzirFilme() {
        System.out.println("Nome do filme: " + this.getNome());
        System.out.println("Descrição: " + this.getDescricao());
        System.out.println("Duração: " + this.getDuracao());
        System.out.println("Nome do diretor: " + this.getDiretor().getNome());
    }

    //GETTERS AND SETTERS
    private String getNome() {
        return nome;
    }

    private String getDescricao() {
        return descricao;
    }

    private int getDuracao() {
        return duracao;
    }

    private int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    private int getAvaliacao() {
        return avaliacao;
    }

    private Diretor getDiretor() {
        return diretor;
    }
}
