package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.exception.*;
import br.com.cwi.reset.gabrielaraujodesouza.exception.genericos.*;
import br.com.cwi.reset.gabrielaraujodesouza.model.Estudio;
import br.com.cwi.reset.gabrielaraujodesouza.repository.EstudioRepository;
import br.com.cwi.reset.gabrielaraujodesouza.request.EstudioRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepository estudioRepository;

    ModelMapper modelMapper = new ModelMapper();

    public void criarEstudio(EstudioRequest estudioRequest) throws Exception {

        if(estudioRepository.findByNomeEqualsIgnoreCase(estudioRequest.getNome()) != null) {
            throw new NomeDuplicadoException(TipoDominioException.ESTUDIO.getSingular(), estudioRequest.getNome());
        }

//        Estudio estudio = new Estudio(
//                estudioRequest.getNome(),
//                estudioRequest.getDescricao(),
//                estudioRequest.getDataCriacao(),
//                estudioRequest.getStatusAtividade());

        Estudio estudio = modelMapper.map(estudioRequest, Estudio.class);
        estudioRepository.save(estudio);
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws ListaVaziaException, FiltroException {

        if (estudioRepository.findAll().isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ESTUDIO.getSingular(), TipoDominioException.ESTUDIO.getPlural());
        }

        final List<Estudio> retorno = new ArrayList<>();

        if(filtroNome!=null){
            for(Estudio estudio : estudioRepository.findByNomeContainingIgnoreCase(filtroNome)) {
                retorno.add(estudio);
            }
            if(retorno.isEmpty()){
                throw new FiltroException("Estudio", filtroNome);
            }
            return retorno;
        } else {
            return estudioRepository.findAll();
        }
    }

    public Estudio consultarEstudio(Integer id) throws IdException, CampoVazioException {

        if(id==null) {
            throw new CampoVazioException("id");
        }

        Optional<Estudio> optionalEstudioProcurado = estudioRepository.findById(id);

        if(optionalEstudioProcurado.isPresent()) {
            return optionalEstudioProcurado.get();
        } else {
            throw new IdException(TipoDominioException.ESTUDIO.getSingular(),id);
        }
    }
}
