public class ItensPorQuantidade {

    /* Esta classe atua como controle de estoque no código.
    Tentei implementar um sistema de cadastro de novos itens, mas como os itens são atributos,
    não consegui encontrar uma maneira de fazer isto. Teria que criar uma classe com um Array de itens,
    para poder dar append num item novo, ou puxar os itens de um DB (eu acho).
    */

    static int pao = Constantes.QTD_PADRAO_PAO;
    static int torta = Constantes.QTD_PADRAO_TORTA; //cada torta possui 16 fatias e começamos com 4 tortas.
    static int sanduiche = Constantes.QTD_PADRAO_SANDUICHE;
    static int leite = Constantes.QTD_PADRAO_LEITE;
    static int cafe = Constantes.QTD_PADRAO_CAFE;

    public static int retornaQuantidadeEmEstoque(String item) {

        /* Esta função verifica qual item se trata
        e retorna a quantidade dele em estoque */

        int quantidadeEmEstoque = 0;

        if (Constantes.STRING_PAO.equals(item)) {
            quantidadeEmEstoque = pao;
        }

        if (Constantes.STRING_TORTA.equals(item)) {
            quantidadeEmEstoque = torta;
        }

        if (Constantes.STRING_SANDUICHE.equals(item)) {
            quantidadeEmEstoque = sanduiche;
        }

        if (Constantes.STRING_LEITE.equals(item)) {
            quantidadeEmEstoque = leite;
        }

        if (Constantes.STRING_CAFE.equals(item)) {
            quantidadeEmEstoque = cafe;
        }

        return quantidadeEmEstoque;
    }

    public static void retiraItem(String item, int quantidade) {

        /* Esta função verifica o item e a quantidade a serem retirados,
        e subtrai esta quantidade do item, contanto que o estoque não fique negativo */

        if (Constantes.STRING_PAO.equals(item)) {
            if (pao - quantidade*60 >= 0) {
                pao -= quantidade*60;        //converte unidades em gramas de pao
            } else {
                pao = 0;
            }
        }

        if (Constantes.STRING_TORTA.equals(item)) {
            if (torta - quantidade >= 0) {
                torta -= quantidade;
            } else {
                torta = 0;
            }
        }

        if (Constantes.STRING_SANDUICHE.equals(item)) {
            if (sanduiche - quantidade >= 0) {
                sanduiche -= quantidade;
            } else {
                sanduiche = 0;
            }
        }

        if (Constantes.STRING_LEITE.equals(item)) {
            if (leite - quantidade >= 0) {
                leite -= quantidade;
            } else {
                leite = 0;
            }
        }

        if (Constantes.STRING_CAFE.equals(item)) {
            if (cafe - quantidade >= 0) {
                cafe -= quantidade;
            } else {
                cafe = 0;
            }
        }
    }
}
