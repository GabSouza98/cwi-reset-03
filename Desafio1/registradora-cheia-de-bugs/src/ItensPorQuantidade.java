public class ItensPorQuantidade {

    static int pao = 3600; //repoe 3600 gramas
    static int torta = 4;  // repoe 4 tortas inteiras, ou seja 64 fatias
    static int sanduiche = 20; //repoe 20 unidades de sanduiches
    static int leite = 20; // a reposicao pode variar de 10 a 50 itens
    static int cafe = 20; // a reposicao pode variar de 10 a 50 itens

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

}
