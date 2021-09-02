package onmove.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.stage.Stage;

public class Cliente implements Serializable {

    public static Stage stage;
    
    private int cdCliente;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate data;
    private String divida;

    public Cliente(){
    }
    
    public Cliente(int cdCliente, String nome, String cpf, LocalDate data, String telefone, String divida) {
        this.cdCliente = cdCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.data = data;
        this.telefone = telefone;
        this.divida = divida;
    }

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getDivida() {
        return divida;
    }

    public void setDivida(String divida) {
        this.divida = divida;
    }
    
    public LocalDate getDataNascimento() {
        return data;
    }

    public void setDataNascimento(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
