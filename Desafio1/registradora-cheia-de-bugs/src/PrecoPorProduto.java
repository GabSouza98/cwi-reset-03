public class PrecoPorProduto {

    //Atributos estáticos pois não será instanciada esta classe.

    private static double precoPao = 12.75;
    private static double precoTorta = 96.0;
    private static double precoSanduiche = 4.5;
    private static double precoLeite = 4.48;
    private static double precoCafe = 9.56;

    public static void alteraPreco(String produto, double novopreco) {

        /* Esta função realiza um polimorfismo, pois também existe na classe Registradora.
        Nesta classe, ela altera o atributo preço do item correspondente.
        */

        if (produto.equals(Constantes.STRING_PAO)) {
            PrecoPorProduto.setPrecoPao(novopreco);
        } else
        if (produto.equals(Constantes.STRING_LEITE)) {
            PrecoPorProduto.setPrecoLeite(novopreco);
        } else
        if (produto.equals(Constantes.STRING_CAFE)) {
            PrecoPorProduto.setPrecoCafe(novopreco);
        } else
        if (produto.equals(Constantes.STRING_SANDUICHE)) {
            PrecoPorProduto.setPrecoSanduiche(novopreco);
        } else
        if (produto.equals(Constantes.STRING_TORTA)) {
            PrecoPorProduto.setPrecoTorta(novopreco);
        } else {
            System.out.println("Digite um produto válido!");
        }

    }

    //GETTERS AND SETTERS

    public static double getPrecoPao() {
        return precoPao;
    }

    public static void setPrecoPao(double precoPao) {
        PrecoPorProduto.precoPao = precoPao;
    }

    public static double getPrecoTorta() {
        return precoTorta;
    }

    public static void setPrecoTorta(double precoTorta) {
        PrecoPorProduto.precoTorta = precoTorta;
    }

    public static double getPrecoSanduiche() {
        return precoSanduiche;
    }

    public static void setPrecoSanduiche(double precoSanduiche) {
        PrecoPorProduto.precoSanduiche = precoSanduiche;
    }

    public static double getPrecoLeite() {
        return precoLeite;
    }

    public static void setPrecoLeite(double precoLeite) {
        PrecoPorProduto.precoLeite = precoLeite;
    }

    public static double getPrecoCafe() {
        return precoCafe;
    }

    public static void setPrecoCafe(double precoCafe) {
        PrecoPorProduto.precoCafe = precoCafe;
    }
}
