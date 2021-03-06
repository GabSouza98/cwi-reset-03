import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Registradora {

    public static void main(String[] args) {

//        Array de Produtos
//        Produtos[] produtos = new Produtos[5]; // Como incluir mais um produto? -> Criar arraylist de Objetos?
//
//        produtos[0] = new Produtos(Constantes.STRING_PAO,Constantes.QTD_MINIMA_PAO,Constantes.QTD_PADRAO_PAO,0.765,Constantes.QTD_PADRAO_PAO);
//        produtos[1] = new Produtos(Constantes.STRING_TORTA,Constantes.QTD_MINIMA_TORTA,Constantes.QTD_PADRAO_TORTA,6.0,Constantes.QTD_PADRAO_TORTA);
//        produtos[2] = new Produtos(Constantes.STRING_LEITE,Constantes.QTD_MINIMA_LEITE,Constantes.QTD_PADRAO_LEITE,4.48,Constantes.QTD_PADRAO_LEITE);
//        produtos[3] = new Produtos(Constantes.STRING_CAFE,Constantes.QTD_MINIMA_CAFE,Constantes.QTD_PADRAO_CAFE,9.56,Constantes.QTD_PADRAO_CAFE);
//        produtos[4] = new Produtos(Constantes.STRING_SANDUICHE,Constantes.QTD_MINIMA_SANDUICHE,Constantes.QTD_PADRAO_SANDUICHE,4.5,Constantes.QTD_PADRAO_SANDUICHE);

        List<Produtos> produtosss = new ArrayList<Produtos>();

        Produtos produto1 = new Produtos(Constantes.STRING_PAO,Constantes.QTD_MINIMA_PAO,Constantes.QTD_PADRAO_PAO,0.765,Constantes.QTD_PADRAO_PAO, true);
        Produtos produto2 = new Produtos(Constantes.STRING_TORTA,Constantes.QTD_MINIMA_TORTA,Constantes.QTD_PADRAO_TORTA,6.0,Constantes.QTD_PADRAO_TORTA, true);
        Produtos produto3 = new Produtos(Constantes.STRING_LEITE,Constantes.QTD_MINIMA_LEITE,Constantes.QTD_PADRAO_LEITE,4.48,Constantes.QTD_PADRAO_LEITE, false);
        Produtos produto4 = new Produtos(Constantes.STRING_CAFE,Constantes.QTD_MINIMA_CAFE,Constantes.QTD_PADRAO_CAFE,9.56,Constantes.QTD_PADRAO_CAFE, false);
        Produtos produto5 = new Produtos(Constantes.STRING_SANDUICHE,Constantes.QTD_MINIMA_SANDUICHE,Constantes.QTD_PADRAO_SANDUICHE,4.5,Constantes.QTD_PADRAO_SANDUICHE, true);

        ArrayList<Produtos> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto3);
        produtos.add(produto4);
        produtos.add(produto5);


          menu( produtos );
//        primeiroBug( produtos );

//        segundoBug(produtos);
//        terceiroBug(produtos);
//
//        quartoBug(produtos);
//        quintoBug(produtos);
//
//        sextoBug(produtos);
    }

    public static void menu( ArrayList<Produtos> arrayProdutos) {

        /* Esta fun????o fornece uma interface shell para o usuario efetuar opera????es como:
        1 - Comprar um produto
        2 - Alterar o pre??o de um produto (mediante login)
        3 - Incluir um produto (mediante login)
        4 - Listar produtos
        5 - Sair do programa
        */

        DataProjeto.criarDataComCozinhaFuncionando();
        boasVindas();
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        while(opcao != 5) {

            if(opcao == 1) {
                comprar( arrayProdutos);
            } else if(opcao == 2) {
                if (logar()) {
                    alteraPreco( arrayProdutos );
                }
            } else if (opcao == 3) {
                if (logar()) {
                    incluiProduto( arrayProdutos );
                }
            } else if (opcao == 4){
                listaProdutos( arrayProdutos );
            }
            else {
                System.out.println("Digite uma op????o v??lida por favor.");
            }
            boasVindas();
            opcao = scanner.nextInt();
        }
        System.out.println("Voc?? saiu do programa. Volte sempre!");
    }

    private static void listaProdutos(ArrayList<Produtos> arrayProdutos) {

        for (int i=0; i<arrayProdutos.size(); i++) {
            System.out.printf("Produto: %s Estoque: %d Pre??o: %.2f%n", arrayProdutos.get(i).getNomeProduto(), arrayProdutos.get(i).getQuantidadeEmEstoque(), arrayProdutos.get(i).getPrecoProduto());
        }
    }

    private static void incluiProduto(ArrayList<Produtos> arrayProdutos) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do produto novo: ");
        String nome = scanner.next();

        System.out.print("Digite a quantidade minima em estoque do produto: ");
        int minimo = scanner.nextInt();

        System.out.print("Digite a quantidade a ser reposta desse produto por batelada: ");
        int reposicao = scanner.nextInt();

        System.out.print("Digite o pre??o do produto por unidade vendida: ");
        double preco = scanner.nextDouble();

        System.out.print("Digite a quantidade atual em estoque deste produto: ");
        int estoque = scanner.nextInt();

        System.out.print("Digite se o produto ?? fornecido pela cozinha da padaria: ");
        boolean itemcozinha = scanner.nextBoolean();

        arrayProdutos.add(new Produtos(nome,minimo,reposicao,preco,estoque,itemcozinha));

        System.out.println("Item adicionado com sucesso!");
    }

    public static void boasVindas() {

        /* Boas vindas e instru????es de utiliza????o do software */
        System.out.println("Bem vindo a padaria Reseter!");
        System.out.println("Digite 1 para realizar uma compra");
        System.out.println("Digite 2 para alterar o pre??o de um produto");
        System.out.println("Digite 3 para incluir um produto novo");
        System.out.println("Digite 4 para listar os produtos");
        System.out.println("Digite 5 para finalizar o programa");
    }

    public static boolean logar() {

        /* Esta fun????o pede as credencias do funcion??rio (login e senha) e retorna
        se os dados inseridos s??o v??lidos */

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

    public static void alteraPreco( ArrayList<Produtos> arrayProdutos  ) {

        /* Esta fun????o pede qual o nome do produto que deseja-se alterar o pre??o e tamb??m o valor do novo pre??o */

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o produto que deseja alterar o preco: ");
        String produto = scanner.next();
        System.out.print("Digite o novo pre??o: ");
        double novoPreco = scanner.nextDouble();
        PrecoPorProduto.alteraPreco(arrayProdutos ,produto, novoPreco);
    }

    public static boolean verificaPermissao(String usuario, String senha) {

        /* Esta fun????o verifica se as credenciais passadas s??o v??lidas e retorna true ou false */

        boolean permissao = false;

        //Claro que o ideal seria verificar num banco de dados se os dados fecham, mas assim j?? da pra brincar
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

        //Verifica se o usu??rio existe
        if (usuarios.contains(usuario)) {
            //Retorna a posi????o do usu??rio no ArrayList de usu??rios, caso ele exista
            int i = usuarios.indexOf(usuario);
            // Verifica se a senha condiz com a senha do usu??rio de mesma posi????o no ArrayList de senhas
            if (senhas.get(i).equals(senha)) {
                permissao = true;
            } else {
                System.out.println("Senha incorreta :(");
            }
        } else {
            //Informa que o usu??rio n??o ?? v??lido
            System.out.println("Usuario n??o encontrado :(");
        }
        return permissao;
    }

    public static void comprar( ArrayList<Produtos> arrayProdutos) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do item que voc?? deseja comprar:");
        System.out.print("Op????es: ");

        for(int i=0;i<arrayProdutos.size();i++){
            System.out.print(arrayProdutos.get(i).getNomeProduto() + " ");
        }

        System.out.println();
        String item = scanner.next();
        System.out.println("Digite a quantidade que deseja comprar");
        int quantidade = scanner.nextInt();
        double precoTotal = registrarItem(arrayProdutos, item, quantidade);
        System.out.printf("Valor total: R$ %.2f%n", precoTotal);
    }

    private static double registrarItem(ArrayList<Produtos> arrayProdutos, String item, int quantidade) {

        double precoItem;
        int quantidadeEmEstoque = ItensPorQuantidade.retornaQuantidadeEmEstoque(arrayProdutos, item);
        if (quantidade>quantidadeEmEstoque) {

            if (checkItemCozinha(arrayProdutos, item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    quantidade = quantidadeEmEstoque;
                } else {
                    while (quantidade<quantidadeEmEstoque) {
                        ReposicaoCozinha.reporItem(arrayProdutos, item);
                        System.out.println("Feita a reposi????o do item " + item);
                        quantidadeEmEstoque = ItensPorQuantidade.retornaQuantidadeEmEstoque(arrayProdutos, item);
                    }
                    //Ap??s garantir que a quantidade em estoque ?? >= ao pedido, prosseguir com o pedido
                }
            } else {
                while (quantidade<quantidadeEmEstoque) {
                    ReposicaoFornecedor.reporItem(arrayProdutos, item);
                    System.out.println("Feita a reposi????o do item " + item);
                    quantidadeEmEstoque = ItensPorQuantidade.retornaQuantidadeEmEstoque(arrayProdutos, item);
                }
            }
        }
        precoItem = efetuarRetiradaEVerificarReposicao(arrayProdutos, item, quantidade);
        return precoItem;
    }

    private static boolean checkItemCozinha(ArrayList<Produtos> arrayProdutos, String item) {

        for (Produtos arrayProduto : arrayProdutos) {
            if (arrayProduto.getNomeProduto().equals(item)) {
                return arrayProduto.getItemCozinha();
            }
        }
        return false;
    }


    private static double efetuarRetiradaEVerificarReposicao( ArrayList<Produtos> arrayProdutos, String item, int quantidade) {

        /* Esta fun????o realiza as opera????es de retira do item do estoque,
        calcula e retorna o pre??o do pedido, e verifica se h?? necessidade de reposi????o
        */

        ItensPorQuantidade.retiraItem(arrayProdutos, item, quantidade);
        double precoItem = RelacaoPesoPreco.retornaPrecoProduto(arrayProdutos, item, quantidade);
        if (QuantidadeMinimaItem.precisaReposicao(arrayProdutos, item)) {
            if (checkItemCozinha(arrayProdutos, item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    System.out.println("Cozinha fechada!");
                    System.out.println("A reposi????o deste item n??o est?? dispon??vel");
                    System.out.printf("Quantidade em estoque: %d%n", ItensPorQuantidade.retornaQuantidadeEmEstoque(arrayProdutos, item));
                } else {
                    ReposicaoCozinha.reporItem(arrayProdutos, item);
                    System.out.println("Feita a reposi????o do item " + item);
                }
            } else {
                ReposicaoFornecedor.reporItem(arrayProdutos, item);
                System.out.println("Feita a reposi????o do item " + item);
            }
        }
        return precoItem;
    }


    private static void primeiroBug( ArrayList<Produtos> arrayProdutos) {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "sanduiche";
        int quantidade = 4;
        double precoTotal = registrarItem(arrayProdutos, item, quantidade);
        System.out.printf("Valor total: %.2f%n", precoTotal);
    }

    private static void segundoBug( ArrayList<Produtos> arrayProdutos) {
        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;

        double precoTotal = registrarItem(arrayProdutos, item, quantidade);

        System.out.printf("Valor total: %.2f%n", precoTotal);
    }

    private static void terceiroBug( ArrayList<Produtos> arrayProdutos) {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "cafe";
        int quantidade = 40;

        double precoTotal = registrarItem(arrayProdutos, item, quantidade);

        System.out.printf("Valor total: %.2f%n", precoTotal);
    }

    private static void quartoBug( ArrayList<Produtos> arrayProdutos) {
        DataProjeto.criarDataComCozinhaFuncionando();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(arrayProdutos, item, quantidade);

        System.out.printf("Valor total: %.2f%n", precoTotal);

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(arrayProdutos, item2, quantidade2);

        System.out.printf("Valor total: %.2f%n", precoTotal2);
    }

    private static void quintoBug( ArrayList<Produtos> arrayProdutos) {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pao";
        int quantidade = 10;

        double precoTotal = registrarItem(arrayProdutos, item, quantidade);

        System.out.printf("Valor total: %.2f%n", precoTotal);
    }

    private static void sextoBug( ArrayList<Produtos> arrayProdutos) {
        DataProjeto.criarDataComCozinhaEncerradaSemDiaUtil();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(arrayProdutos, item, quantidade);

        System.out.printf("Valor total: %.2f%n", precoTotal);

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(arrayProdutos, item2, quantidade2);

        System.out.printf("Valor total: %.2f%n", precoTotal2);
    }

}
