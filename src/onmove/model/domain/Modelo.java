package onmove.model.domain;

import java.io.Serializable;

public class Modelo implements Serializable {

    private int cdModelo;
    private String descricao;
    
    public Modelo(){
    }

    public Modelo(int cdModelo, String descricao) {
        this.cdModelo = cdModelo;
        this.descricao = descricao;
    }

    public int getCdModelo() {
        return cdModelo;
    }

    public void setCdModelo(int cdModelo) {
        this.cdModelo = cdModelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return this.descricao;
    }
    
}
