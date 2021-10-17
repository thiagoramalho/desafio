package br.com.coopersystem.util;

public class Resposta {

    private Cotacao dataInformada;
    private Cotacao dataAnterior;

    public Resposta(Cotacao dataInformada, Cotacao dataAnterior) {
        this.dataInformada = dataInformada;
        this.dataAnterior = dataAnterior;
    }

    public Cotacao getDataInformada() {
        return dataInformada;
    }

    public void setDataInformada(Cotacao dataInformada) {
        this.dataInformada = dataInformada;
    }

    public Cotacao getDataAnterior() {
        return dataAnterior;
    }

    public void setDataAnterior(Cotacao dataAnterior) {
        this.dataAnterior = dataAnterior;
    }

}
