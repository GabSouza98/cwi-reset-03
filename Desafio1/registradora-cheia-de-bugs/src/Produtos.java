public class Produtos {

    private String nomeProduto;
    private int quantidadeMinima;
    private int quantidadeReposicao;
    private double precoProduto;
    private int quantidadeEmEstoque;
    private boolean itemcozinha;

    public Produtos(String nomeProduto, int quantidadeMinima, int quantidadeReposicao, double precoProduto, int quantidadeEmEstoque, boolean itemcozinha) {
        this.nomeProduto = nomeProduto;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeReposicao = quantidadeReposicao;
        this.precoProduto = precoProduto;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.itemcozinha = itemcozinha;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    private void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    private void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public int getQuantidadeReposicao() {
        return quantidadeReposicao;
    }

    private void setQuantidadeReposicao(int quantidadeReposicao) {
        this.quantidadeReposicao = quantidadeReposicao;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public boolean getItemCozinha() {
        return itemcozinha;
    }

    private void setItemcozinha(boolean itemcozinha) {
        this.itemcozinha = itemcozinha;
    }
}
