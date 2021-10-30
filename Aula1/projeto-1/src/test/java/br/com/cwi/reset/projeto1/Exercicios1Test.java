package br.com.cwi.reset.projeto1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Exercicios1Test {

    @Test
    void testSomarListaComNumerosInteirosEPositivos() {

        Exercicios1 exercicio = new Exercicios1();
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        Integer result = exercicio.somarLista(numeros);
        Integer expected = 15;

        assertEquals(result , expected);
    }

    @Test
    void testSomarListaComNumerosInteirosPositivosENegativos() {

        Exercicios1 exercicio = new Exercicios1();
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, -5);

        Integer result = exercicio.somarLista(numeros);
        Integer expected = 5;

        assertEquals(result , expected);
    }

    @Test
    void testSomarListaComNumerosNegativos() {

        Exercicios1 exercicio = new Exercicios1();
        List<Integer> numeros = Arrays.asList(-1, -2, -3);

        Integer result = exercicio.somarLista(numeros);
        Integer expected = -6;

        assertEquals(result , expected);
    }

    @Test
    void testSomarListaComTodosOsElementosZero() {

        Exercicios1 exercicio = new Exercicios1();
        List<Integer> numeros = Arrays.asList(0, 0, 0);

        Integer result = exercicio.somarLista(numeros);
        Integer expected = 0;

        assertEquals(result , expected);
    }

    @Test
    void testSomarListaComListaVazia() {

        Exercicios1 exercicio = new Exercicios1();
        List<Integer> numeros = Arrays.asList();

        Integer result = exercicio.somarLista(numeros);
        Integer expected = 0;

        assertEquals(result , expected);
    }

    @Test
    void testCalcularMediaComNumerosInteirosEPositivos() {

        Exercicios1 exercicio = new Exercicios1();
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        Double result = exercicio.calcularMedia(numeros);
        Double expected = 3.0;

        assertEquals(result , expected);
    }

    @Test
    void testCalcularMediaComNumerosInteirosPositivosENegativos() {

        Exercicios1 exercicio = new Exercicios1();
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, -5);

        Double result = exercicio.calcularMedia(numeros);
        Double expected = 1.0;

        assertEquals(result , expected);
    }

    @Test
    void testCalcularMediaComNumerosNegativos() {

        Exercicios1 exercicio = new Exercicios1();
        List<Integer> numeros = Arrays.asList(-1, -3, -5);

        Double result = exercicio.calcularMedia(numeros);
        Double expected = -3.0;

        assertEquals(result , expected);
    }

    @Test
    void testCalcularMediaComTodosOsElementosZero() {

        Exercicios1 exercicio = new Exercicios1();
        List<Integer> numeros = Arrays.asList(0, 0, 0);

        Double result = exercicio.calcularMedia(numeros);
        Double expected = 0.0;

        assertEquals(result , expected);
    }

    @Test
    void testCalcularMediaComListaVazia() {

        Exercicios1 exercicio = new Exercicios1();
        List<Integer> numeros = Arrays.asList();

        try {
            Double result = exercicio.calcularMedia(numeros);
        } catch (ArithmeticException e) {
            assertEquals("Divisao por zero", e.getMessage());
        }

    }

    @Test
    void testObterPalavraInvertidaAbacate() {

        Exercicios1 exercicio = new Exercicios1();
        String result = exercicio.obterPalavraInvertida("Abacate");
        String expected = "etacabA";
        assertEquals(result, expected);
    }

    @Test
    void testObterPalavraInvertidaBanana() {

        Exercicios1 exercicio = new Exercicios1();
        String result = exercicio.obterPalavraInvertida("Banana");
        String expected = "ananaB";
        assertEquals(result, expected);
    }

    @Test
    void testObterPalavraInvertidaPessego() {

        Exercicios1 exercicio = new Exercicios1();
        String result = exercicio.obterPalavraInvertida("Pessego");
        String expected = "ogesseP";
        assertEquals(result, expected);
    }

    @Test
    void testObterPalavraInvertidaMorango() {

        Exercicios1 exercicio = new Exercicios1();
        String result = exercicio.obterPalavraInvertida("Morango");
        String expected = "ognaroM";
        assertEquals(result, expected);
    }


}
