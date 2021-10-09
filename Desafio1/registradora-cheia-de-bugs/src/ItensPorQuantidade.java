import java.util.ArrayList;

public class ItensPorQuantidade {

    public static int retornaQuantidadeEmEstoque(ArrayList<Produtos> arrayProdutos, String item) {



        int quantidadeEmEstoque = 0;

        for (int i=0; i<arrayProdutos.size(); i++) {
            if(arrayProdutos.get(i).getNomeProduto().equals(item)) {
                quantidadeEmEstoque = arrayProdutos.get(i).getQuantidadeEmEstoque();
            }
        }
        return quantidadeEmEstoque;
    }

    public static void retiraItem( ArrayList<Produtos> arrayProdutos, String item, int quantidade) {

        for (int i=0; i<arrayProdutos.size(); i++) {
            if(arrayProdutos.get(i).getNomeProduto().equals(item)) {
                if(arrayProdutos.get(i).getQuantidadeEmEstoque() - quantidade >=0 ) {
                    arrayProdutos.get(i).setQuantidadeEmEstoque(arrayProdutos.get(i).getQuantidadeEmEstoque() - quantidade);
                }
                else {
                    arrayProdutos.get(i).setQuantidadeEmEstoque(0);
                }
            }
        }
    }
}
