package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.domain.Filme;
import br.com.cwi.reset.projeto1.exception.AtorJaExistente;
import br.com.cwi.reset.projeto1.exception.FilmeJaExistenteException;
import br.com.cwi.reset.projeto1.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @PostMapping
    public ResponseEntity<Ator> cadastrarAtor(@RequestBody Ator ator) throws AtorJaExistente {

        Ator atorSalvo = atorService.cadastrarAtor(ator);
        return ResponseEntity.ok(atorSalvo);
    }

    @GetMapping
    public List<Ator> consultarAtores() {
        return atorService.consultarAtores();
    }

    @GetMapping("/by-name/{nome}")
    public Ator consultarAtor(@PathVariable String nome) {
        return atorService.consultarAtorPorNome(nome);
    }

    @GetMapping("/by-oscars/{numeroOscars}")
    public List<Ator> consultarAtorPorOscar(@PathVariable Integer numeroOscars) {
        return atorService.consultarPorNumeroOscars(numeroOscars);
    }

    @GetMapping("/by-filter")
    public List<Ator> consultarPorFiltro(@RequestParam Integer numeroOscars, @RequestParam Integer anoNascimento) {
        return atorService.consultarPorFiltro(numeroOscars,anoNascimento);
    }



}
