package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistente;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNomeNuloException;
import br.com.cwi.reset.projeto1.repository.PetRepository;
import br.com.cwi.reset.projeto1.repository.PetRepositoryBd;
import br.com.cwi.reset.projeto1.repository.PetRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepositoryBd repository;

    public Pet buscarPorNome(String nome) throws PetNaoExistenteException {

        Pet petProcurado = repository.findByNome(nome);
        return petProcurado;
    }

    public Pet cadastrarPet(Pet pet) throws PetNomeNuloException, PetNaoExistenteException, PetJaExistente {

        if(pet.getNome() == null ) {
            throw new PetNomeNuloException();
        }
        if(buscarPorNome(pet.getNome()) != null) {
            throw new PetJaExistente(pet.getNome());
        }

        repository.save(pet);
        return pet;
    }

    public List<Pet> buscarPets() {
        return repository.findAll();
    }

    public Pet atualizar(Pet pet) throws PetNaoExistenteException {

        Pet petJaCadastrado = buscarPorNome(pet.getNome());

        if (petJaCadastrado == null) {
            throw new PetNaoExistenteException(pet.getNome());
        }
        return repository.save(pet);
    }

    public void deletar(String nome) throws PetNaoExistenteException {
        Pet petJaCadastrado = buscarPorNome(nome);
        if (petJaCadastrado == null) {
            throw new PetNaoExistenteException(nome);
        }
        repository.delete(petJaCadastrado);
    }
}
