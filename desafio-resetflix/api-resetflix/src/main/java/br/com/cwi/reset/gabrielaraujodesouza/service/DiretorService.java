package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor.DiretorAtreladoAFilmeException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.*;
import br.com.cwi.reset.gabrielaraujodesouza.model.Diretor;
import br.com.cwi.reset.gabrielaraujodesouza.repository.DiretorRepository;
import br.com.cwi.reset.gabrielaraujodesouza.request.DiretorRequest;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoDiretor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;
    @Autowired
    private FilmeService filmeService;

    ModelMapper modelMapper = new ModelMapper();

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws Exception {

        new ValidacaoDiretor().accept(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade(), TipoDominioException.DIRETOR);

        if(diretorRepository.findByNomeEqualsIgnoreCase(diretorRequest.getNome()) != null) {
            throw new NomeDuplicadoException(TipoDominioException.DIRETOR.getSingular(), diretorRequest.getNome());
        }

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

    public Diretor consultarDiretor(Integer id) throws IdException, CampoVazioException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        Optional<Diretor> optionalDiretorProcurado = diretorRepository.findById(id);

        if((optionalDiretorProcurado.isPresent())){
            return optionalDiretorProcurado.get();
        } else{
            throw new IdException(TipoDominioException.DIRETOR.getSingular(),id);
        }
    }

    public void atualizarDiretor(Integer id, DiretorRequest diretorRequest) throws Exception {

        Diretor diretorCadastrado = consultarDiretor(id);

        new ValidacaoDiretor().accept(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade(), TipoDominioException.DIRETOR);

        if (!diretorCadastrado.getNome().equals(diretorRequest.getNome())) {
            if (!isNull(diretorRepository.findByNomeEqualsIgnoreCase(diretorRequest.getNome()))) {
                throw new NomeDuplicadoException(TipoDominioException.DIRETOR.getSingular(), diretorRequest.getNome());
            }
        }

        Diretor diretorAtualizado = new Diretor(
                diretorRequest.getNome(),
                diretorRequest.getDataNascimento(),
                diretorRequest.getAnoInicioAtividade());

        diretorAtualizado.setId(id);
        diretorRepository.save(diretorAtualizado);
    }

    public void removerDiretores(Integer id) throws CampoVazioException, IdException, DiretorAtreladoAFilmeException {
        Diretor diretorCadastrado = consultarDiretor(id);

        if (filmeService.consultarDiretor(diretorCadastrado)) {
            diretorRepository.delete(diretorCadastrado);
        } else {
            throw new DiretorAtreladoAFilmeException();
        }



    }


}
