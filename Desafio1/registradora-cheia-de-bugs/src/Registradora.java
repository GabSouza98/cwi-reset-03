import java.util.ArrayList;
import java.util.Scanner;

public class Registradora {

    public static void main(String[] args) {


//          menu();
//        primeiroBug();

//        segundoBug();
//        terceiroBug();
//
//        quartoBug();
//
//        quintoBug();
//
        sextoBug();
    }

    public static void menu() {

        /* Esta função fornece uma interface shell para o usuario efetuar operações como:
        1 - Comprar um produto
        2 - Alterar o preço de um produto (mediante login)
        3 - Sair do programa
        */

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

        /* Boas vindas e instruções de utilização do software */
        System.out.println("Bem vindo a padaria Reseter!");
        System.out.println("Digite 1 para realizar uma compra");
        System.out.println("Digite 2 para alterar o preço de um produto");
        System.out.println("Digite 3 para finalizar o programa");
    }

    public static boolean logar() {

        /* Esta função pede as credencias do funcionário (login e senha) e retorna
        se os dados inseridos são válidos */

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

        /* Esta função pede qual o nome do produto que deseja-se alterar o preço e também o valor do novo preço */

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o produto que deseja alterar o preco: ");
        String produto = scanner.next();
        System.out.print("Digite o novo preço: ");
        double novoPreco = scanner.nextDouble();
        PrecoPorProduto.alteraPreco(produto, novoPreco);
    }

    public static boolean verificaPermissao(String usuario, String senha) {

        /* Esta função verifica se as credenciais passadas são válidas e retorna true ou false */

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

        //Verifica se o usuário existe
        if (usuarios.contains(usuario)) {
            //Retorna a posição do usuário no ArrayList de usuários, caso ele exista
            int i = usuarios.indexOf(usuario);
            // Verifica se a senha condiz com a senha do usuário de mesma posição no ArrayList de senhas
            if (senhas.get(i).equals(senha)) {
                permissao = true;
            } else {
                System.out.println("Senha incorreta :(");
            }
        } else {
            //Informa que o usuário não é válido
            System.out.println("Usuario não encontrado :(");
        }
        return permissao;
    }

    public static void comprar() {

        /* Esta função fornece os nomes dos itens existentes, e pergunta qual item e quantidade deseja-se comprar.
        O mais legal seria ter esses itens num arraylist e mostrar numa interface gráfica, para impedir de o usuario
        digitar o nome errado de um item,
        OU,
        Colocar um sistema de busca pelo nome, e o sistema vai afunilando as opções existentes. As opções teriam que
        vir de um banco de dados */

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

        /* Esta função verifica se é possível registrar um item no caixa, comparando a quantidade pedida
        com a quantidade em estoque. Se a quantidade pedida for maior que o disponível, verifica se é possível
        efetuar a reposição do item, de acordo com o tipo do item e o horário de funcionamento da padaria.
        Se a quantidade for menor ou igual ao disponível, efetua a retirada normalmente.
        */

        //Cria variavel de preço vazia, para ser preenchida nos blocos de IF
        double precoItem = 0;
        //Verifica se a quantidade pedida é maior que a quantidade em estoque
        int quantidadeEmEstoque = ItensPorQuantidade.retornaQuantidadeEmEstoque(item);
        if (quantidade>quantidadeEmEstoque) {

            //CASO EM QUE A QUANTIDADE PEDIDA NÃO ESTÁ DISPONÍVEL NO ESTOQUE
            //Necessário completar o que falta do pedido para poder retirar

            //Verifica se é item de cozinha
            if (checkItemCozinha(item)) {
                //Verifica se a cozinha não está em funcionamento
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    //Define a quantidade pedida como a quantidade em estoque, pois não é possível repor.
                    quantidade = quantidadeEmEstoque;
                    precoItem = efetuarRetiradaEVerificarReposicao(item, quantidade);
                } else {
                    //Se a cozinha estiver em funcionamento, faça a
                    //reposição do estoque enquanto a quantidade pedida for menor que o estoque
                    while (quantidade<quantidadeEmEstoque) {
                        ReposicaoCozinha.reporItem(item);
                        System.out.println("Feita a reposição do item " + item);
                        quantidadeEmEstoque = ItensPorQuantidade.retornaQuantidadeEmEstoque(item);
                    }
                    //Após garantir que a quantidade em estoque é >= ao pedido, prosseguir com o pedido
                    precoItem = efetuarRetiradaEVerificarReposicao(item, quantidade);
                }
            }
            if (checkItemFornecedor(item)) {
                while (quantidade<quantidadeEmEstoque) {
                    ReposicaoFornecedor.reporItem(item);
                    System.out.println("Feita a reposição do item " + item);
                    quantidadeEmEstoque = ItensPorQuantidade.retornaQuantidadeEmEstoque(item);
                }
                precoItem = efetuarRetiradaEVerificarReposicao(item, quantidade);
            }


        } else {
            //CASO EM QUE A QUANTIDADE PEDIDA ESTÁ DISPONÍVEL NO ESTOQUE
            //Método para descontar do estoque a quantidade pedida deste item
            precoItem = efetuarRetiradaEVerificarReposicao(item, quantidade);

        }
        return precoItem;
    }

    private static boolean checkItemCozinha(String item) {

        /* Esta função verifica se o item é de responsabilidade da cozinha */

        if (Constantes.STRING_PAO.equals(item) || Constantes.STRING_SANDUICHE.equals(item) || Constantes.STRING_TORTA.equals(item) ) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean checkItemFornecedor(String item) {

        /* Esta função verifica se o item é de responsabilidade do fornecedor */

        if (Constantes.STRING_LEITE.equals(item) || Constantes.STRING_CAFE.equals(item)) {
            return true;
        } else {
            return false;
        }
    }

    private static double efetuarRetiradaEVerificarReposicao(String item, int quantidade) {

        /* Esta função realiza as operações de retira do item do estoque,
        calcula e retorna o preço do pedido, e verifica se há necessidade de reposição
        */

        ItensPorQuantidade.retiraItem(item, quantidade);
        double precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);
        if (QuantidadeMinimaItem.precisaReposicao(item)) {
            if (checkItemCozinha(item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    System.out.println("Cozinha fechada!");
                    System.out.println("A reposição deste item não está disponível");
                    System.out.println(String.format("Quantidade em estoque: %d", ItensPorQuantidade.retornaQuantidadeEmEstoque(item)));
                } else {
                    ReposicaoCozinha.reporItem(item);
                    System.out.println("Feita a reposição do item " + item);
                }
            }
            if (checkItemFornecedor(item)) {
                ReposicaoFornecedor.reporItem(item);
                System.out.println("Feita a reposição do item " + item);
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
