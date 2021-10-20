package br.com.cwi.reset.gabrielaraujodesouza.controller;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.CampoVazioException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.FiltroException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.IdException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.ListaVaziaException;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
import br.com.cwi.reset.gabrielaraujodesouza.request.EstudioRequest;
import br.com.cwi.reset.gabrielaraujodesouza.service.EstudioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    private EstudioService estudioService;

    public EstudioController() {
        this.estudioService = new EstudioService(FakeDatabase.getInstance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody EstudioRequest estudioRequest) throws Exception {
        this.estudioService.criarEstudio(estudioRequest);
    }

    @GetMapping
    public List<Estudio> consultarEstudios(@RequestParam(required = false) String filtroNome) throws ListaVaziaException, FiltroException {
        return this.estudioService.consultarEstudios(filtroNome);
    }

    @GetMapping("/{id}")
    public Estudio consultarEstudio(@PathVariable Integer id) throws CampoVazioException, IdException {
        return this.estudioService.consultarEstudio(id);
    }

}
