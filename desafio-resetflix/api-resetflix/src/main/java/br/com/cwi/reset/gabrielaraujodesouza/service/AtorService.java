package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.*;
import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.repository.AtorRepository;
import br.com.cwi.reset.gabrielaraujodesouza.request.AtorRequest;
import br.com.cwi.reset.gabrielaraujodesouza.model.StatusCarreira;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.response.AtorEmAtividade;
import br.com.cwi.reset.gabrielaraujodesouza.validator.ValidacaoAtor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    ModelMapper modelMapper = new ModelMapper();

    public void criarAtor(AtorRequest atorRequest) throws Exception {

        new ValidacaoAtor().accept(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getAnoInicioAtividade(), TipoDominioException.ATOR);

        if(atorRepository.findByNomeEqualsIgnoreCase(atorRequest.getNome()) != null) {
            throw new NomeDuplicadoException(TipoDominioException.ATOR.getSingular(), atorRequest.getNome());
        }

//        Ator ator = new Ator(
//                    atorRequest.getNome(),
//                    atorRequest.getDataNascimento(),
//                    atorRequest.getStatusCarreira(),
//                    atorRequest.getAnoInicioAtividade()
//                    );
        Ator ator = modelMapper.map(atorRequest, Ator.class);
        atorRepository.save(ator);
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws ListaVaziaException, FiltroException {

        final List<Ator> atoresCadastrados = atorRepository.findAll();

        if (atoresCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }

        List<AtorEmAtividade> retorno = new ArrayList<>();

        if (filtroNome != null) {

            for (Ator ator : atorRepository.findByStatusCarreiraAndNomeContainingIgnoreCase(StatusCarreira.EM_ATIVIDADE, filtroNome)){
                retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
            }
            if (retorno.isEmpty()) {
                throw new FiltroException("Ator", filtroNome);
            }
            return retorno;

        } else {
            for (Ator ator : atorRepository.findByStatusCarreira(StatusCarreira.EM_ATIVIDADE)){
                retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
            }
            return retorno;
        }
    }

    public Optional<Ator> consultarAtor(Integer id) throws IdException, CampoVazioException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        Optional<Ator> atorProcurado = atorRepository.findById(id);
        if((atorProcurado.isPresent())){
            return atorProcurado;
        } else{
            throw new IdException(TipoDominioException.ATOR.getSingular(),id);
        }
    }

    public List<Ator> consultarAtores() throws ListaVaziaException {
        List<Ator> atores = atorRepository.findAll();
        if (atores.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ATOR.singular, TipoDominioException.ATOR.plural);
        } else {
            return atores;
        }
    }
}
