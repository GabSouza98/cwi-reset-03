public class ReposicaoCozinha {

    static void reporItem(String item) {

        /* Esta função soma a quantidade produzida de um item ao seu estoque atual */

        if ("pao".equals(item)) {
            ItensPorQuantidade.pao += 3600;
        }
        if ("torta".equals(item)) {
            ItensPorQuantidade.torta += 64;
        }
        if ("sanduiche".equals(item)) {
            ItensPorQuantidade.sanduiche += 20;
        }
    }
}
