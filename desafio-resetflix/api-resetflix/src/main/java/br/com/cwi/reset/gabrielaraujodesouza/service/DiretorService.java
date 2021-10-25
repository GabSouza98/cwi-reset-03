package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.*;
import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.repository.AtorRepository;
import br.com.cwi.reset.gabrielaraujodesouza.repository.DiretorRepository;
import br.com.cwi.reset.gabrielaraujodesouza.request.DiretorRequest;
import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoDiretor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;

    ModelMapper modelMapper = new ModelMapper();

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws Exception {

        new ValidacaoDiretor().accept(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade(), TipoDominioException.DIRETOR);

        if(diretorRepository.findByNomeEqualsIgnoreCase(diretorRequest.getNome()) != null) {
            throw new NomeDuplicadoException(TipoDominioException.DIRETOR.getSingular(), diretorRequest.getNome());
        }

//        Diretor diretor = new Diretor(
//                diretorRequest.getNome(),
//                diretorRequest.getDataNascimento(),
//                diretorRequest.getAnoInicioAtividade());
        Diretor diretor = modelMapper.map(diretorRequest, Diretor.class);
        diretorRepository.save(diretor);
    }

    public List<Diretor> listarDiretores(String filtroNome) throws ListaVaziaException, FiltroException {

        if (diretorRepository.findAll().isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }

        final List<Diretor> retorno = new ArrayList<>();

        if(filtroNome!=null){
            for(Diretor diretor : diretorRepository.findByNomeContainingIgnoreCase(filtroNome)) {
                retorno.add(diretor);
                }
            if (retorno.isEmpty()) {
                throw new FiltroException("Diretor", filtroNome);
            }
            return retorno;
        } else {
            return diretorRepository.findAll();
        }
    }

    public Optional<Diretor> consultarDiretor(Integer id) throws IdException, CampoVazioException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        Optional<Diretor> diretorProcurado = diretorRepository.findById(id);
        if((!diretorProcurado.isPresent())){
            throw new IdException(TipoDominioException.DIRETOR.getSingular(),id);
        } else{
            return diretorProcurado;
        }
    }
}
