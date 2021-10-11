package br.com.cwi.reset.gabrielaraujodesouza.exception;

public class IdException extends Exception{
    public IdException(String nomeClasse, Integer id) {
        super(String.format("Nenhum %s encontrado com o parâmetro id=%d, favor verifique os parâmetros informados.", nomeClasse,id));
    }
}
