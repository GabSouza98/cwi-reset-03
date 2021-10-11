package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DiretorService {

    public static Integer id = 1;

    private FakeDatabase fakeDatabase;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws NomeVazioException, DataNascimentoNula, AnoInicioAtividadeNull, DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, DiretorDuplicadoException, SemSobrenomeDiretorException {

        if(diretorRequest.getNome().isEmpty()) {
            throw new NomeVazioException();
        }

        if(diretorRequest.getDataNascimento()==null){
            throw new DataNascimentoNula();
        }

        if(diretorRequest.getAnoInicioAtividade()==null){
            throw new AnoInicioAtividadeNull();
        }

        String[] palavras = diretorRequest.getNome().split("\\s+");
        if(palavras.length < 2) {
            throw new SemSobrenomeDiretorException();
        }

        if(ChronoUnit.DAYS.between(diretorRequest.getDataNascimento(), LocalDate.now()) < 0) {
            System.out.println("Não é possível cadastrar diretores não nascidos.");
            throw new DataNascimentoMaiorQueAtualException();
        }

        if(diretorRequest.getAnoInicioAtividade() - diretorRequest.getDataNascimento().getYear() < 0) {
            System.out.println("Ano de início de atividade inválido para o diretor cadastrado.");
            throw new AnoInicioAtividadoAntesDeDataNascimentoException();
        }

        for(Diretor d : fakeDatabase.recuperaDiretores()){
            if (d.getNome().equals(diretorRequest.getNome())) {
                System.out.println("Já existe um diretor cadastrado para o nome " + diretorRequest.getNome() +".");
                throw new DiretorDuplicadoException();
            }
        }

        Diretor diretor = new Diretor(id++,
                diretorRequest.getNome(),
                diretorRequest.getDataNascimento(),
                diretorRequest.getAnoInicioAtividade());
        this.fakeDatabase.persisteDiretor(diretor);
    }

}
