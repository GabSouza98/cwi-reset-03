import java.util.Random;

public class ReposicaoFornecedor {

    static void reporItem(String item) {

        /* Esta função soma a quantidade produzida de um item ao seu estoque atual */

        Random random = new Random();

        if (Constantes.STRING_LEITE.equals(item)) {
            ItensPorQuantidade.leite += random.nextInt(40) + 10;
        }

        if (Constantes.STRING_CAFE.equals(item)) {
            ItensPorQuantidade.cafe += random.nextInt(40) + 10;
        }
    }
}
