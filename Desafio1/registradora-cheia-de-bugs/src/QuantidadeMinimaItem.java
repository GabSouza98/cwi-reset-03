public class QuantidadeMinimaItem {

    public static boolean precisaReposicao(String item) {

        /* Esta função verifica se cada item está em quantidade acima do mínimo
        definido pela política de estoque */

        if (Constantes.STRING_PAO.equals(item)) {
            return ItensPorQuantidade.pao < Constantes.QTD_MINIMA_PAO;
        }

        if (Constantes.STRING_TORTA.equals(item)) {
            return ItensPorQuantidade.torta < Constantes.QTD_MINIMA_TORTA;
        }

        if (Constantes.STRING_SANDUICHE.equals(item)) {
            return ItensPorQuantidade.sanduiche <= Constantes.QTD_MINIMA_SANDUICHE;
        }

        if (Constantes.STRING_LEITE.equals(item)) {
            return ItensPorQuantidade.leite < Constantes.QTD_MINIMA_LEITE;
        }

        if (Constantes.STRING_CAFE.equals(item)) {
            return ItensPorQuantidade.cafe < Constantes.QTD_MINIMA_CAFE;
        }

        return false;
    }
}
