package br.com.cwi.reset.gabrielaraujodesouza.controller;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.CampoVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.FiltroException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.IdException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.ListaVaziaException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.request.AtorRequest;
import br.com.cwi.reset.gabrielaraujodesouza.response.AtorEmAtividade;
import br.com.cwi.reset.gabrielaraujodesouza.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@Valid @RequestBody AtorRequest atorRequest) throws Exception {
        this.atorService.criarAtor(atorRequest);
    }

    @GetMapping
    public List<Ator> consultarAtores() throws ListaVaziaException {
        return this.atorService.consultarAtores();
    }

    @GetMapping("/em_atividade")
    public List<AtorEmAtividade> listarAtoresEmAtividade(@RequestParam(required = false) String filtroNome) throws ListaVaziaException, FiltroException {
        return this.atorService.listarAtoresEmAtividade(filtroNome);
    }

    @GetMapping("/{id}")
    public Ator consultarAtor(@PathVariable(required = true) Integer id) throws CampoVazioException, IdException {
        return this.atorService.consultarAtor(id);
    }

    @PutMapping("/{id}")
    public void atualizarAtor(@PathVariable Integer id, @Valid @RequestBody AtorRequest atorRequest) throws Exception {
        this.atorService.atualizarAtor(id, atorRequest);
    }

    @DeleteMapping("/{id}")
    public void removerAtor(@PathVariable
                            @NotNull(message="Campo obrigatório não informado. Favor informar o campo id")
                            Integer id) throws Exception {
        this.atorService.removerAtor(id);
    }


}
