package br.com.cwi.reset.gabrielaraujodesouza.exception;

import javax.xml.crypto.Data;

public class DataNascimentoNula extends Exception{

    public DataNascimentoNula() {
        super("Campo obrigatório não informado. Favor informar o campo Data Nascimento");
    }



}
