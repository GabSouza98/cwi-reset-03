package br.com.cwi.reset.gabrielaraujodesouza.controller;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.CampoVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.FiltroException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.IdException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.ListaVaziaException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.request.AtorRequest;
import br.com.cwi.reset.gabrielaraujodesouza.request.DiretorRequest;
import br.com.cwi.reset.gabrielaraujodesouza.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor(@Valid @RequestBody DiretorRequest diretorRequest) throws Exception {
        this.diretorService.cadastrarDiretor(diretorRequest);
    }

    @GetMapping
    public List<Diretor> listarDiretores(@RequestParam(required = false) String filtroNome) throws ListaVaziaException, FiltroException {
        return this.diretorService.listarDiretores(filtroNome);
    }

    @GetMapping("/{id}")
    public Diretor consultarDiretor(@PathVariable Integer id) throws ListaVaziaException, CampoVazioException, IdException {
        return this.diretorService.consultarDiretor(id);
    }

    @PutMapping("/{id}")
    public void atualizarDiretor(@PathVariable Integer id, @Valid @RequestBody DiretorRequest diretorRequest) throws Exception {
        this.diretorService.atualizarDiretor(id, diretorRequest);
    }

    @DeleteMapping("/{id}")
    public void removerDiretores(@PathVariable Integer id) throws Exception {
        this.diretorService.removerDiretores(id);
    }

}
