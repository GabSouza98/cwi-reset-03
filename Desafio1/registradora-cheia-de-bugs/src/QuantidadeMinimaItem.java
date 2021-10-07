public class QuantidadeMinimaItem {

    public static boolean precisaReposicao(String item) {

        /* Esta função verifica se cada item está em quantidade acima do mínimo
        definido pela política de estoque */

        if ("pao".equals(item)) {
            return ItensPorQuantidade.pao < 600;
        }

        if ("torta".equals(item)) {
            return ItensPorQuantidade.torta < 10;
        }

        if ("sanduiche".equals(item)) {
            return ItensPorQuantidade.sanduiche <= 1;
        }

        if ("cafe".equals(item)) {
            return ItensPorQuantidade.leite < 12;
        }

        if ("leite".equals(item)) {
            return ItensPorQuantidade.cafe < 12;
        }

        return false;
    }
}
