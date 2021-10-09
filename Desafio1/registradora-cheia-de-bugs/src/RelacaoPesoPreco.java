import java.util.ArrayList;

public class RelacaoPesoPreco {

    public static double retornaPrecoProduto(ArrayList<Produtos> arrayProdutos, String item, int qtd) {

        double precoTotal = 0;

        for (Produtos arrayProduto : arrayProdutos) {
            if (arrayProduto.getNomeProduto().equals(item)) {
                precoTotal = arrayProduto.getPrecoProduto() * qtd;
            }
        }
        return precoTotal;
    }
}
