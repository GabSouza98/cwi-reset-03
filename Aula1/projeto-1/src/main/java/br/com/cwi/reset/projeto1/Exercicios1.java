package br.com.cwi.reset.projeto1;

import java.util.Arrays;
import java.util.List;

public class Exercicios1 {

    public Integer somarLista(List<Integer> numeros) {

        int soma = 0;
        for (int numero : numeros) {
            soma += numero;
        }
        return soma;
    }

    public Double calcularMedia(List<Integer> numeros) {

        int soma = somarLista(numeros);
        int quantidadeElementos = numeros.size();
        if(quantidadeElementos==0) {
            throw new ArithmeticException("Divisao por zero");
        }
        double media = soma/quantidadeElementos;
        return media;
    }

    public Integer obterMaiorNumero(List<Integer> numeros) {

        int maiorNumero = 0;
        for(int numero : numeros) {
            if (numero > maiorNumero) {
                maiorNumero = numero;
            }
        }
        return maiorNumero;
    }

    public String obterPalavraInvertida(String palavra) {

        String palavraInvertida = "";
        int tamanho = palavra.length();

//        for (int i = tamanho - 1; i>=0; i--) {
//            palavraInvertida = palavraInvertida + palavra.charAt(i);
//        }

        for (int i = 0; i <= tamanho - 1; i++) {
            palavraInvertida = palavraInvertida + palavra.charAt(tamanho - 1 - i);
        }
        return palavraInvertida;
    }

    public List<Integer> ordenarLista(List<Integer> numeros) {

        boolean trocou = true;
        int iteracoes = 0;
        while (trocou) {
            iteracoes += 1;
            trocou = false;
            for (int i=0; i<numeros.size() - 1; i++) {
                //System.out.println("estamos no indice " + i);
                if(numeros.get(i) > numeros.get(i+1)) {
                    int pos1 = numeros.get(i);
                    int pos2 = numeros.get(i+1);
                    numeros.set(i, pos2);
                    numeros.set(i+1, pos1);
                    trocou = true;
                }
            }
        }

        System.out.println("Foram necessarias " + iteracoes + " iteracoes");

        return numeros;
    }
}

