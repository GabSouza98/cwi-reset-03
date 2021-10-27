package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.exception.ator_e_diretor.AtorAtraladoAPersonagemException;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.*;
import br.com.cwi.reset.gabrielaraujodesouza.model.Ator;
import br.com.cwi.reset.gabrielaraujodesouza.model.PersonagemAtor;
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

    @Autowired
    private PersonagemService personagemService;

    ModelMapper modelMapper = new ModelMapper();

    public void criarAtor(AtorRequest atorRequest) throws Exception {

        new ValidacaoAtor().accept(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getAnoInicioAtividade(), TipoDominioException.ATOR);

        if(atorRepository.findByNomeEqualsIgnoreCase(atorRequest.getNome()) != null) {
            throw new NomeDuplicadoException(TipoDominioException.ATOR.getSingular(), atorRequest.getNome());
        }

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

    public Ator consultarAtor(Integer id) throws IdException, CampoVazioException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        Optional<Ator> optionalAtorProcurado = atorRepository.findById(id);
        if((optionalAtorProcurado.isPresent())){
            return optionalAtorProcurado.get();
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

    public void atualizarAtor(Integer id, AtorRequest atorRequest) throws Exception {

        Ator atorCadastrado = consultarAtor(id);

        new ValidacaoAtor().accept(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getAnoInicioAtividade(), TipoDominioException.ATOR);

        //se o nome for igual ao cadastrado, não é necessario verificar o banco.
        //se o nome for diferente, é necessário verificar se não vai ser igual a outro ator que já tenha.
        if(!atorCadastrado.getNome().equals(atorRequest.getNome())) {
            if(!isNull(atorRepository.findByNomeEqualsIgnoreCase(atorRequest.getNome()))) {
                throw new NomeDuplicadoException(TipoDominioException.ATOR.getSingular(), atorRequest.getNome());
            }
        }

        Ator atorAtualizado = new Ator(id,
                atorRequest.getNome(),
                atorRequest.getDataNascimento(),
                atorRequest.getStatusCarreira(),
                atorRequest.getAnoInicioAtividade());

        atorRepository.save(atorAtualizado);
    }

    public void removerAtor(Integer id) throws CampoVazioException, IdException, AtorAtraladoAPersonagemException {
        Ator atorCadastrado = consultarAtor(id);

        if (personagemService.consultarPersonagemAtor(atorCadastrado)) {
            atorRepository.delete(atorCadastrado);
        } else {
            throw new AtorAtraladoAPersonagemException();
        }





    }
}
