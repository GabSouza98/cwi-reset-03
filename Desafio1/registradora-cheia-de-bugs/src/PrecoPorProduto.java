import java.util.ArrayList;

public class PrecoPorProduto {

    public static void alteraPreco(ArrayList<Produtos> arrayProdutos, String produto, double novopreco) {

        for (int i=0; i<arrayProdutos.size(); i++) {
            if(arrayProdutos.get(i).getNomeProduto().equals(produto)) {
                arrayProdutos.get(i).setPrecoProduto(novopreco);
            }
        }
    }
}
