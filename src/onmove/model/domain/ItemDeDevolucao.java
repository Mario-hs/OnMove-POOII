package onmove.model.domain;

import java.io.Serializable;

public class ItemDeDevolucao implements Serializable {

    private int cdItemDeDevolucao;
    private int quantidade;
    private double valor;
    private Bicicleta bicicleta;
    private Devolucao devolucao;

    public ItemDeDevolucao() {
    }

    public int getCdItemDeDevolucao() {
        return cdItemDeDevolucao;
    }

    public void setCdItemDeDevolucao(int cdItemDeDevolucao) {
        this.cdItemDeDevolucao = cdItemDeDevolucao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    public Devolucao getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(Devolucao devolucao) {
        this.devolucao = devolucao;
    }
    
}
