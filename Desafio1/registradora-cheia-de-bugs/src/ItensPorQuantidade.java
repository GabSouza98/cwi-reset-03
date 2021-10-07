public class ItensPorQuantidade {

    /* Esta classe atua como controle de estoque no código.
    Tentei implementar um sistema de cadastro de novos itens, mas como os itens são atributos,
    não consegui encontrar uma maneira de fazer isto. Teria que criar uma classe com um Array de itens,
    para poder dar append num item novo, ou puxar os itens de um DB (eu acho).
    */

    static int pao = 3600;
    static int torta = 64; //cada torta possui 16 fatias e começamos com 4 tortas.
    static int sanduiche = 20;
    static int leite = 20;
    static int cafe = 20;

    public static int retornaQuantidadeEmEstoque(String item) {

        /* Esta função verifica qual item se trata
        e retorna a quantidade dele em estoque */

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

        /* Esta função verifica o item e a quantidade a serem retirados,
        e subtrai esta quantidade do item, contanto que o estoque não fique negativo */

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
