package onmove.model.domain;

import java.io.Serializable;

public class Marca implements Serializable {

    private int cdMarca;
    private String nome;
    
    public Marca(){
    }

    public Marca(int cdMarca, String nome) {
        this.cdMarca = cdMarca;
        this.nome = nome;
    }

    public int getCdMarca() {
        return cdMarca;
    }

    public void setCdMarca(int cdMarca) {
        this.cdMarca = cdMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
    
}
