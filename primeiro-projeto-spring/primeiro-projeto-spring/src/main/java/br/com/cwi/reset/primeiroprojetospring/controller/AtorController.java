package br.com.cwi.reset.primeiroprojetospring.controller;


import br.com.cwi.reset.primeiroprojetospring.FakeDatabase;
import br.com.cwi.reset.primeiroprojetospring.request.AtorRequest;
import br.com.cwi.reset.primeiroprojetospring.service.AtorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atores")
public class AtorController {

    private AtorService atorService;
    public AtorController() {
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody AtorRequest atorRequest) throws Exception {
        this.atorService.criarAtor(atorRequest);
    }
}
