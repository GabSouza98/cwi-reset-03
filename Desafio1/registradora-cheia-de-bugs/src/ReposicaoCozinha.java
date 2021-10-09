import java.util.ArrayList;

public class ReposicaoCozinha {

    static void reporItem(ArrayList<Produtos> arrayProdutos, String item) {

        for (int i=0; i<arrayProdutos.size(); i++) {
            if(arrayProdutos.get(i).getNomeProduto().equals(item)) {
                arrayProdutos.get(i).setQuantidadeEmEstoque(arrayProdutos.get(i).getQuantidadeEmEstoque() + arrayProdutos.get(i).getQuantidadeReposicao() );
            }
        }
    }
}
