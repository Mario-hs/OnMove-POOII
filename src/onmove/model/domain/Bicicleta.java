package onmove.model.domain;


import java.io.Serializable;

public class Bicicleta implements Serializable {

    private int cdBicicleta;
    private String nome;
    private Marca marca;
    private double preco;
    private int quantidade;
    private Modelo modelo;

    public Bicicleta() {
    }

    public Bicicleta(int cdBicicleta, String nome, double preco, int quantidade, Marca marca, Modelo modelo) {
        this.cdBicicleta = cdBicicleta;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getCdBicicleta() {
        return cdBicicleta;
    }

    public void setCdBicicleta(int cdBicicleta) {
        this.cdBicicleta = cdBicicleta;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public Marca getMarca() {
        return marca;
    }
    
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }
    
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
    
}
