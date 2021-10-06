import java.util.ArrayList;
import java.util.Scanner;

public class Registradora {

    public static void main(String[] args) {


//          menu();
//        primeiroBug();

        segundoBug();

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
                comprar();
            } else if(opcao == 2) {
                if (logar()) {
                    alteraPreco();
                }
            } else {
                System.out.println("Digite uma opção válida por favor.");
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

    public static boolean logar() {
        boolean acesso = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, identifique-se:");
        System.out.print("Digite seu usuario: ");
        String usuario = scanner.next();
        System.out.print("Digite sua senha: ");
        String senha = scanner.next();  //mais tarde tentarei implementar algo que deixe a senha como "*"
        if (verificaPermissao(usuario, senha)) {
            acesso = true;
        }
        return acesso;
    }

    public static void alteraPreco() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o produto que deseja alterar o preco: ");
        String produto = scanner.next();
        System.out.print("Digite o novo preço: ");
        double novoPreco = scanner.nextDouble();
        PrecoPorProduto.alteraPreco(produto, novoPreco);
    }

    public static boolean verificaPermissao(String usuario, String senha) {

        boolean permissao = false;

        //Claro que o ideal seria verificar num banco de dados se os dados fecham, mas assim já da pra brincar
        ArrayList<String> usuarios = new ArrayList<>();
        usuarios.add("Gabriel");
        usuarios.add("Neto");
        usuarios.add("Amanda");
        usuarios.add("admin");

        ArrayList<String> senhas = new ArrayList<>();
        senhas.add("123");
        senhas.add("456");
        senhas.add("789");
        senhas.add("admin");

        if (usuarios.contains(usuario)) {
            int i = usuarios.indexOf(usuario); //retorna a posição do usuario, se houver um usuario com esse nome
            if (senhas.get(i).equals(senha)) {
                permissao = true;
            } else {
                System.out.println("Senha incorreta :(");
            }
        } else {
            System.out.println("Usuario não encontrado :(");
        }
        return permissao;
    }

    public static void comprar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do item que você deseja comprar");
        System.out.println("Opções: pao, torta, sanduiche, leite, cafe");
        String item = scanner.next();
        System.out.println("Digite a quantidade que deseja comprar");
        int quantidade = scanner.nextInt();
        double precoTotal = registrarItem(item, quantidade);
        System.out.println(String.format("Valor total: R$ %.2f", precoTotal));
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
        String item = "sanduiche";
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
