import java.util.ArrayList;
import java.util.Random;

public class ReposicaoFornecedor {

    static void reporItem(ArrayList<Produtos> arrayProdutos, String item) {

        Random random = new Random();

        for (int i=0; i<arrayProdutos.size(); i++) {
            if(arrayProdutos.get(i).getNomeProduto().equals(item)) {
                arrayProdutos.get(i).setQuantidadeEmEstoque(arrayProdutos.get(i).getQuantidadeEmEstoque() + arrayProdutos.get(i).getQuantidadeReposicao() );
            }
        }
    }
}
