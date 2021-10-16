package br.com.cwi.reset.primeiroprojetospring.controller;

import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;
import br.com.cwi.reset.primeiroprojetospring.exceptions.AvaliacaoForaDoPadraoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    private static List<Filme> filmes = new ArrayList<>();

    private Filme buscarFilmePeloNome(String nome) {
        for (Filme f : filmes) {
            if (f.getNome().equals(nome)) {
                return f;
            }
        }
        return null;
    }

    @GetMapping
    public List<Filme> retornaFilmes() {
        return filmes;
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Filme> getById(@PathVariable String nome) {

        Filme filme = buscarFilmePeloNome(nome);
        if (filme == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filme);
    }

    @PostMapping
    public ResponseEntity<Filme> cadastraFilme(@RequestBody Filme filme) {

        Filme filmeCadastrado = buscarFilmePeloNome(filme.getNome());
        if (filmeCadastrado != null) {
            return ResponseEntity.badRequest().build();
        }
        filmes.add(filme);
        return ResponseEntity.ok(filme);
    }

    @PutMapping()
    public Filme atualizarFilme(@RequestBody Filme filme) {
        Filme filmeCadastrado = buscarFilmePeloNome(filme.getNome());

        if (filmeCadastrado != null) {
            filmes.remove(filmeCadastrado);
            filmes.add(filme);
            return filme;
        }
        return null;
    }

    @DeleteMapping("/{nome}")
    public void deletarFilme(@PathVariable String nome) {
        Filme filme = buscarFilmePeloNome(nome);
        if (filme != null) {
            filmes.remove(filme);
        }
    }








}
