public class RelacaoPesoPreco {

    public static double retornaPrecoProduto(String item, int qtd) {

        double precoTotal = 0;

        //É vendido por kilo, e cada pao pesa 60g. Cliente solicita a quantidade de paes.
        if ("pao".equals(item)) {
            precoTotal = PrecoPorProduto.getPrecoPao() * (qtd * (double) 60 / 1000);
        }

        //É vendido por fatia, e cada torta tem 16 fatias. Cliente solicita a quantidade de fatias.
        if ("torta".equals(item)) {
            precoTotal = PrecoPorProduto.getPrecoTorta() * ( (double) qtd / 16);
        }

        //Vendido por unidade. O cliente pede a quantidade.
        if ("leite".equals(item)) {
            precoTotal = (double) PrecoPorProduto.getPrecoLeite() * qtd;
        }

        //Vendido por unidade. O cliente pede a quantidade.
        if ("cafe".equals(item)) {
            precoTotal = (double) PrecoPorProduto.getPrecoCafe() * qtd;
        }

        //Vendido por unidade. O cliente pede a quantidade.
        if ("sanduiche".equals(item)) {
            precoTotal = (double) PrecoPorProduto.getPrecoSanduiche() * qtd;
        }

        return precoTotal;
    }
}
