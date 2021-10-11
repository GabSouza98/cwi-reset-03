package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class DataNascimentoMaiorQueAtualException extends Exception{

    public DataNascimentoMaiorQueAtualException(String nomeClasse) {
        super(String.format("Não é possivel cadastrar %s não nascidos.",nomeClasse));
    }
}
