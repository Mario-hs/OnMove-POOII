//package outros.model.domain;
package onmove.model.domain;


import onmove.model.domain.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Aluguel implements Serializable {

    private int cdAluguel;
    private LocalDate data;
    private double valor;
    private boolean pago;
    private List<ItemDeAluguel> itensDeAluguel;
    private Cliente cliente;

    public Aluguel() {
    }

    public Aluguel(int cdaluguel, LocalDate data, double valor, boolean pago) {
        this.cdAluguel = cdaluguel;
        this.data = data;
        this.valor = valor;
        this.pago = pago;
    }

    public int getCdAluguel() {
        return cdAluguel;
    }

    public void setCdAluguel(int cdAluguel) {
        this.cdAluguel = cdAluguel;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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

    public List<ItemDeAluguel> getItensDeAluguel() {
        return itensDeAluguel;
    }

    public void setItensDeAluguel(List<ItemDeAluguel> itensDeAluguel) {
        this.itensDeAluguel = itensDeAluguel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
