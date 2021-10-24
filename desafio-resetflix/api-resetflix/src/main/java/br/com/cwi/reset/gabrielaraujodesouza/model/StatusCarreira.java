package br.com.cwi.reset.gabrielaraujodesouza.model;

public enum StatusCarreira {
    EM_ATIVIDADE("em_atividade"),
    APOSENTADO("aposentado");

    private String singular;

    StatusCarreira(String singular) {
        this.singular = singular;
    }

    public String getSingular() {
        return singular;
    }

    public void setSingular(String singular) {
        this.singular = singular;
    }
}


