package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class DataNascimentoMaiorQueAtualException extends Exception{

    public DataNascimentoMaiorQueAtualException() {
        super("Não é possivel cadastrar pessoas não nascidas");
    }
}
