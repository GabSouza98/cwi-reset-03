public class RelacaoPesoPreco {

    public static double retornaPrecoProduto(String item, int qtd) {
        double precoTotal = 0;

        //É vendido por kilo, e cada pao pesa 60g. Cliente solicita a quantidade de paes.
        if ("pao".equals(item)) {
            precoTotal = 12.75 * (qtd * (double) 60 / 1000);
        }

        //É vendido por fatia, e cada torta tem 16 fatias. Cliente solicita a quantidade de fatias.
        if ("torta".equals(item)) {
            precoTotal = 96.00 * ( (double) qtd / 16);
        }

        //Vendido por unidade. O cliente pede a quantidade.
        if ("leite".equals(item)) {
            precoTotal = (double) 4.48 * qtd;
        }

        //Vendido por unidade. O cliente pede a quantidade.
        if ("cafe".equals(item)) {
            precoTotal = (double) 9.56 * qtd;
        }

        //Vendido por unidade. O cliente pede a quantidade.
        if ("sanduiche".equals(item)) {
            precoTotal = (double) 4.5 * qtd;
        }

        return precoTotal;
    }
}
