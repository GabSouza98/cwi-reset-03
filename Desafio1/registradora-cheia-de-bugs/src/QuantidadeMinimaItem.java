import java.util.ArrayList;

public class QuantidadeMinimaItem {

    public static boolean precisaReposicao(ArrayList<Produtos> arrayProdutos, String item) {

        for (int i=0; i<arrayProdutos.size(); i++) {
            if(arrayProdutos.get(i).getNomeProduto().equals(item)) {
                return arrayProdutos.get(i).getQuantidadeEmEstoque() < arrayProdutos.get(i).getQuantidadeMinima();
            }
        }
        return false;
    }
}
