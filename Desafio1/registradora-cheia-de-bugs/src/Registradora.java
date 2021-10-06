import java.util.Scanner;

public class Registradora {

    public static void main(String[] args) {


          menu();
//        primeiroBug();

//        segundoBug();

//        terceiroBug();
//
//        quartoBug();
//
//        quintoBug();
//
//        sextoBug();
    }

    public static void menu() {

        DataProjeto.criarDataComCozinhaFuncionando();
        boasVindas();
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        while(opcao != 3) {

            if(opcao == 1) {
                System.out.println("Digite o nome do item que você deseja comprar");
                System.out.println("Opções: pao, torta, sanduiche, leite, cafe");
                String item = scanner.next();
                System.out.println("Digite a quantidade que deseja comprar");
                int quantidade = scanner.nextInt();
                double precoTotal = registrarItem(item, quantidade);
                System.out.println(String.format("Valor total: %.2f", precoTotal));

            } else if(opcao == 2) {
                break;
            } else {
                System.out.println("Digite um valor válido por favor.");
            }
            boasVindas();
            opcao = scanner.nextInt();
        }
        System.out.println("Você saiu do programa. Volte sempre!");
    }

    public static void boasVindas() {
        System.out.println("Bem vindo a padaria Reseter!");
        System.out.println("Digite 1 para realizar uma compra");
        System.out.println("Digite 2 para alterar o preço de um produto");
        System.out.println("Digite 3 para finalizar o programa");
    }

    private static double registrarItem(String item, int quantidade) {

        //Verifica se a quantidade pedida não é maior que a quantidade em estoque
        int quantidadeEmEstoque = ItensPorQuantidade.retornaQuantidadeEmEstoque(item);
        if (quantidade>quantidadeEmEstoque) {
            quantidade = quantidadeEmEstoque;
        }

        //Método para descontar do estoque a quantidade pedida deste item
        ItensPorQuantidade.retiraItem(item, quantidade);

        double precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);

        if (QuantidadeMinimaItem.precisaReposicao(item)) {
            if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    System.out.println("Cozinha fechada!");
                    System.out.println("A reposição deste item não está disponível");

                    System.out.println(String.format("Quantidade em estoque: %d", quantidadeEmEstoque - quantidade));
                } else {
                    ReposicaoCozinha.reporItem(item);
                    System.out.println("Feita a reposição do item " +item);
                }
            }

            if ("leite".equals(item) || "cafe".equals(item)) {
                ReposicaoFornecedor.reporItem(item);
            }
        }

        return precoItem;
    }

    private static void primeiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pao";
        int quantidade = 4;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void segundoBug() {
        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void terceiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "cafe";
        int quantidade = 40;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void quartoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
    }

    private static void quintoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pao";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void sextoBug() {
        DataProjeto.criarDataComCozinhaEncerradaSemDiaUtil();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
    }

}
