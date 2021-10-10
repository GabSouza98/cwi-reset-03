package br.com.cwi.reset.gabrielaraujodesouza.domain;

import br.com.cwi.reset.gabrielaraujodesouza.exception.NomeVazioException;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AtorService {

    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) throws NomeVazioException {


        if(atorRequest.getNome().isEmpty()) {
            //se estiver vazio
            throw new NomeVazioException();
        }

        if(atorRequest.getDataNascimento()==null){
            //se estiver vazio
        }

        if(atorRequest.getStatusCarreira()==null){
            //se estiver vazio
        }

        if(atorRequest.getAnoInicioAtividade()!=null){
            //se estiver vazio
        }


        String[] palavras = atorRequest.getNome().split("\\s+");
        if(palavras.length < 2) {
            System.out.println("Deve ser informado no mínimo nome e sobrenome para o ator.");
        }

        if(Period.between(atorRequest.getDataNascimento(), LocalDate.now()).getYears() < 0) {
            //se for menor que zero
            System.out.println("Não é possível cadastrar atores não nascidos.");
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy");
        LocalDate anoInicioAtividade = LocalDate.parse(atorRequest.getAnoInicioAtividade().toString(),dateFormatter);
        if(Period.between(atorRequest.getDataNascimento(), anoInicioAtividade).getYears() < 0) {
            //a atividade nao pode iniciar antes de nascer
            System.out.println("Ano de início de atividade inválido para o ator cadastrado.");
        }

        List<Ator> atores = fakeDatabase.recuperaAtores();
        for(Ator a : atores){
            if (a.getNome().equals(atorRequest.getNome())) {
                //Nome duplicado
                System.out.println("Já existe um ator cadastrado para o nome " + atorRequest.getNome() +".");
            }
        }

        Ator ator = new Ator(atorRequest.getNome(),
                    atorRequest.getDataNascimento(),
                    atorRequest.getStatusCarreira(),
                    atorRequest.getAnoInicioAtividade());
        this.fakeDatabase.persisteAtor(ator);
    }

    // Demais métodos da classe
}
