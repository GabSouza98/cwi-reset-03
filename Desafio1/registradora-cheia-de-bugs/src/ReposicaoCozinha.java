public class ReposicaoCozinha {

    static void reporItem(String item) {

        /* Esta função soma a quantidade produzida de um item ao seu estoque atual */

        if (Constantes.STRING_PAO.equals(item)) {
            ItensPorQuantidade.pao += 3600;
        }
        if (Constantes.STRING_TORTA.equals(item)) {
            ItensPorQuantidade.torta += 64;
        }
        if (Constantes.STRING_SANDUICHE.equals(item)) {
            ItensPorQuantidade.sanduiche += 20;
        }
    }
}
