package br.com.cwi.reset.primeiroprojetospring.controller;

import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;
import br.com.cwi.reset.primeiroprojetospring.exceptions.AvaliacaoForaDoPadraoException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @GetMapping
    public Filme retornaFilme() throws AvaliacaoForaDoPadraoException {
        return new Filme("A volta dos que não foram",
                "Nao sei",
                90,
                1980,
                5.0,
                new Diretor("Tim Burton",
                        LocalDate.of(1968, Month.SEPTEMBER, 25),
                        10,
                        Genero.MASCULINO
                        )
        );
    }




}
