package onmove.model.domain;

import java.io.Serializable;

public class ItemDeAluguel implements Serializable {

    private int cdItemDeAluguel;
    private int quantidade;
    private double valor;
    private Bicicleta bicicleta;
    private Aluguel aluguel;

    public ItemDeAluguel() {
    }

    public int getCdItemDeAluguel() {
        return cdItemDeAluguel;
    }

    public void setCdItemDeAluguel(int cdItemDeAluguel) {
        this.cdItemDeAluguel = cdItemDeAluguel;
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

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }
    
}
