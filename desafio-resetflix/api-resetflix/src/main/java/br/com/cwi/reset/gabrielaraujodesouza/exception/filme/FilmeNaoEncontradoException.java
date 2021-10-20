package br.com.cwi.reset.gabrielaraujodesouza.exception.filme;

public class FilmeNaoEncontradoException extends Exception{

    public FilmeNaoEncontradoException(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) {
        super(String.format("Filme não encontrado com os filtros nomeFilme=%s, nomeDiretor=%s, nomePersonagem=%s, nomeAtor=%s, favor informar outros filtros.",
                nomeFilme,
                nomeDiretor,
                nomePersonagem,
                nomeAtor));
    }
}
