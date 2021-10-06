public class ItensPorQuantidade {

    static int pao = 3600;
    static int torta = 64; //cada torta possui 16 fatias e começamos com 4 tortas.
    static int sanduiche = 20;
    static int leite = 20;
    static int cafe = 20;
    public static int retornaQuantidadeEmEstoque(String item) {

        int quantidadeEmEstoque = 0;

        if ("pao".equals(item)) {
            quantidadeEmEstoque = pao;
        }

        if ("torta".equals(item)) {
            quantidadeEmEstoque = torta;
        }

        if ("sanduiche".equals(item)) {
            quantidadeEmEstoque = sanduiche;
        }

        if ("leite".equals(item)) {
            quantidadeEmEstoque = leite;
        }

        if ("cafe".equals(item)) {
            quantidadeEmEstoque = cafe;
        }

        return quantidadeEmEstoque;
    }

    public static void retiraItem(String item, int quantidade) {

        if ("pao".equals(item)) {
            if (pao - quantidade*60 >= 0) {
                pao -= quantidade*60;        //converte unidades em gramas de pao
            } else {
                pao = 0;
            }
        }

        if ("torta".equals(item)) {
            if (torta - quantidade >= 0) {
                torta -= quantidade;
            } else {
                torta = 0;
            }
        }

        if ("sanduiche".equals(item)) {
            if (sanduiche - quantidade >= 0) {
                sanduiche -= quantidade;
            } else {
                sanduiche = 0;
            }
        }

        if ("leite".equals(item)) {
            if (leite - quantidade >= 0) {
                leite -= quantidade;
            } else {
                leite = 0;
            }
        }

        if ("cafe".equals(item)) {
            if (cafe - quantidade >= 0) {
                cafe -= quantidade;
            } else {
                cafe = 0;
            }
        }

    }

}
