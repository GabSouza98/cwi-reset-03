package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class DataNascimentoMaiorQueAtualException extends Exception{

    public DataNascimentoMaiorQueAtualException() {
        super("Não é possível cadastrar atores não nascidos.");
    }
}
