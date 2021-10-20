package br.com.cwi.reset.gabrielaraujodesouza.exception.filme;

public class ParAtorPersonagemDuplicadoException extends Exception{
    public ParAtorPersonagemDuplicadoException() {
        super("Não é permitido informar o mesmo ator/personagem mais de uma vez para o mesmo filme.");
    }
}
