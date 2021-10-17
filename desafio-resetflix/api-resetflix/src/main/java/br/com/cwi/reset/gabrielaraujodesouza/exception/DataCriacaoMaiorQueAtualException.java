package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class DataCriacaoMaiorQueAtualException extends Exception{

    public DataCriacaoMaiorQueAtualException(String nomeClasse) {
        super(String.format("Não é possível cadastrar %s do futuro.",nomeClasse));
    }

}
