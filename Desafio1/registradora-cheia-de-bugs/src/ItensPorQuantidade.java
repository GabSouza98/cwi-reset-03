public class ItensPorQuantidade {

    static int pao = 3600;
    static int torta = 4;
    static int sanduiche = 20;
    static int leite = 20;
    static int cafe = 20;
    public static int retornaQuantidadeEmEstoque(String item) {

        int quantidadeEmEstoque = 0;

        if ("pao".equals(item)) {
            quantidadeEmEstoque = pao;
            System.out.println(String.format("Temos %d gramas de pao." + pao ));
        }

        if ("torta".equals(item)) {
            quantidadeEmEstoque = torta;
            System.out.println(String.format("Temos %d fatias de torta." + torta ));
        }

        if ("sanduiche".equals(item)) {
            quantidadeEmEstoque = sanduiche;
            System.out.println(String.format("Temos %d sanduiches prontos." + sanduiche ));
        }

        if ("leite".equals(item)) {
            quantidadeEmEstoque = leite;
            System.out.println(String.format("Temos %d unidades de leite." + leite ));
        }

        if ("cafe".equals(item)) {
            quantidadeEmEstoque = cafe;
            System.out.println(String.format("Temos %d unidades de cafe." + cafe ));
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
