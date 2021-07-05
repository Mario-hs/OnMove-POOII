package onmove.model.domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Devolucao implements Serializable {

    private int cdDevolucao;
    private LocalDate dataDevolucao;
    private double valor;
    private boolean pago;
    private List<ItemDeDevolucao> itensDeDevolucao;
    private Cliente cliente;
    private Bicicleta bicicleta;
    private Aluguel aluguel;

    public Devolucao() {
    }

    public Devolucao(int cdDevolucao, LocalDate dataDevolucao, double valor, boolean pago) {
        this.cdDevolucao = cdDevolucao;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
        this.pago = pago;
    }

    public int getCdDevolucao() {
        return cdDevolucao;
    }

    public void setCdDevolucao(int cdDevolucao) {
        this.cdDevolucao = cdDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public List<ItemDeDevolucao> getItensDeDevolucao() {
        return itensDeDevolucao;
    }

    public void setItensDeDevolucao(List<ItemDeDevolucao> itensDeDevolucao) {
        this.itensDeDevolucao = itensDeDevolucao;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }
}
