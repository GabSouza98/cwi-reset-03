package br.com.cwi.reset.gabrielaraujodesouza.controller;

import br.com.cwi.reset.gabrielaraujodesouza.exception.filme.FilmeNaoEncontradoException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.IdException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.ListaVaziaException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Filme;
import br.com.cwi.reset.gabrielaraujodesouza.request.FilmeRequest;
import br.com.cwi.reset.gabrielaraujodesouza.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme(@Valid @RequestBody FilmeRequest filmeRequest) throws Exception {
        this.filmeService.criarFilme(filmeRequest);
    }

    @GetMapping
    public List<Filme> consultarFilmes(@RequestParam(required = false, defaultValue = "") String nomeFilme,
                                       @RequestParam(required = false, defaultValue = "") String nomeDiretor,
                                       @RequestParam(required = false, defaultValue = "") String nomePersonagem,
                                       @RequestParam(required = false, defaultValue = "") String nomeAtor) throws ListaVaziaException, FilmeNaoEncontradoException {
        return this.filmeService.consultarFilmes(nomeFilme,nomeDiretor,nomePersonagem,nomeAtor);
    }

    @DeleteMapping("/{id}")
    public void removerFilme(@PathVariable Integer id) throws IdException {
        this.filmeService.removerFilme(id);
    }


}
