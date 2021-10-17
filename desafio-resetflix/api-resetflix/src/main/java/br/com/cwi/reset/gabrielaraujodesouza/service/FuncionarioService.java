package br.com.cwi.reset.gabrielaraujodesouza.service;

import br.com.cwi.reset.gabrielaraujodesouza.FakeDatabase;
import br.com.cwi.reset.gabrielaraujodesouza.request.FuncionarioRequest;
import br.com.cwi.reset.gabrielaraujodesouza.model.TipoFuncionarios;
import br.com.cwi.reset.gabrielaraujodesouza.exception.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class FuncionarioService {

    protected FakeDatabase fakeDatabase;

    public FuncionarioService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void verificarDados(FuncionarioRequest funcionarioRequest) throws DataNascimentoMaiorQueAtualException, AnoInicioAtividadoAntesDeDataNascimentoException, CampoVazioException, SemSobrenomeException, NomeDuplicadoException {

        if(funcionarioRequest.getNome().isEmpty()) {
            throw new NomeVazioException();
        }

        if(funcionarioRequest.getDataNascimento()==null){
            throw new DataNascimentoVazioException();
        }

        if(funcionarioRequest.getAnoInicioAtividade()==null){
            throw new AnoInicioAtividadeVazioException();
        }

        if(ChronoUnit.DAYS.between(funcionarioRequest.getDataNascimento(), LocalDate.now()) < 0) {
            if(this.getClass().getSimpleName().equals("AtorService")) {
                throw new DataNascimentoMaiorQueAtualException(TipoFuncionarios.ATOR.plural);
            } else if (this.getClass().getSimpleName().equals("DiretorService")) {
                throw new DataNascimentoMaiorQueAtualException(TipoFuncionarios.DIRETOR.plural);
            }
        }

        String[] palavras = funcionarioRequest.getNome().split("\\s+");
        if(palavras.length < 2) {
            if(this.getClass().getSimpleName().equals("AtorService")) {
                throw new SemSobrenomeException(TipoFuncionarios.ATOR.singular);
            } else if (this.getClass().getSimpleName().equals("DiretorService")){
                throw new SemSobrenomeException(TipoFuncionarios.DIRETOR.singular);
            }
        }

        if(funcionarioRequest.getAnoInicioAtividade() - funcionarioRequest.getDataNascimento().getYear() < 0) {
            if(this.getClass().getSimpleName().equals("AtorService")) {
                throw new AnoInicioAtividadoAntesDeDataNascimentoException(TipoFuncionarios.ATOR.singular);
            } else if (this.getClass().getSimpleName().equals("DiretorService")){
                throw new AnoInicioAtividadoAntesDeDataNascimentoException(TipoFuncionarios.DIRETOR.singular);
            }
        }
    }


}
