package onmove.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import onmove.model.domain.Modelo;
import onmove.model.domain.Bicicleta;
import onmove.model.domain.Marca;

public class BicicletaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Bicicleta bicicleta) {
        String sql = "INSERT INTO bicicletas(nome, preco, quantidade, cdMarca, cdModelo) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, bicicleta.getNome());
            stmt.setDouble(2, bicicleta.getPreco());
            stmt.setInt(3, bicicleta.getQuantidade());
            stmt.setInt(4, bicicleta.getMarca().getCdMarca());
            stmt.setInt(5, bicicleta.getModelo().getCdModelo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Bicicleta bicicleta) {
        String sql = "UPDATE bicicletas SET nome=?, preco=?, quantidade=?, cdMarca=?, cdModelo=? WHERE cdBicicleta=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, bicicleta.getNome());
            stmt.setDouble(2, bicicleta.getPreco());
            stmt.setInt(3, bicicleta.getQuantidade());
            stmt.setInt(4, bicicleta.getMarca().getCdMarca());
            stmt.setInt(5, bicicleta.getModelo().getCdModelo());
            stmt.setInt(6, bicicleta.getCdBicicleta());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Bicicleta bicicleta) {
        String sql = "DELETE FROM bicicletas WHERE cdBicicleta=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bicicleta.getCdBicicleta());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não foi possível remover bicicleta! Cadastro com pendências!");
            alert.show();
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Bicicleta> listar() {
        String sql = "SELECT * FROM bicicletas";
        List<Bicicleta> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Bicicleta bicicleta = new Bicicleta();
                Marca marca = new Marca();
                Modelo modelo = new Modelo();
                
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                bicicleta.setNome(resultado.getString("nome"));
                marca.setCdMarca(resultado.getInt("cdMarca"));
                modelo.setCdModelo(resultado.getInt("cdModelo"));
                bicicleta.setPreco(resultado.getDouble("preco"));
                bicicleta.setQuantidade(resultado.getInt("quantidade"));
                
                //Obtendo os dados completos das Marcas e Modelos associada a Bicicleta
                MarcaDAO marcaDAO = new MarcaDAO();
                marcaDAO.setConnection(connection);
                marca = marcaDAO.buscar(marca);
                
                ModeloDAO modeloDAO = new ModeloDAO();
                modeloDAO.setConnection(connection);
                modelo = modeloDAO.buscar(modelo);
                
                bicicleta.setMarca(marca);
                bicicleta.setModelo(modelo);
                retorno.add(bicicleta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Bicicleta> listarBicicletasDisponiveis() {
        String sql = "SELECT * FROM bicicletas";
        List<Bicicleta> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Bicicleta bicicleta = new Bicicleta();
                Marca marca = new Marca();
                Modelo modelo = new Modelo();
                
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                bicicleta.setNome(resultado.getString("nome"));
                marca.setCdMarca(resultado.getInt("cdMarca"));
                modelo.setCdModelo(resultado.getInt("cdModelo"));
                bicicleta.setPreco(resultado.getDouble("preco"));
                bicicleta.setQuantidade(resultado.getInt("quantidade"));
                
                //Obtendo os dados completos das Marcas e Modelos associada a Bicicleta
                MarcaDAO marcaDAO = new MarcaDAO();
                marcaDAO.setConnection(connection);
                marca = marcaDAO.buscar(marca);
                
                ModeloDAO modeloDAO = new ModeloDAO();
                modeloDAO.setConnection(connection);
                modelo = modeloDAO.buscar(modelo);
                
                bicicleta.setMarca(marca);
                bicicleta.setModelo(modelo);
                retorno.add(bicicleta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
     public List<Bicicleta> listarBicicletasAlugadas() {
        String sql = "SELECT * FROM alugueis WHERE cdBicicleta";
        List<Bicicleta> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Bicicleta bicicleta = new Bicicleta();
                Marca marca = new Marca();
                Modelo modelo = new Modelo();
                
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                bicicleta.setNome(resultado.getString("nome"));
                marca.setCdMarca(resultado.getInt("cdMarca"));
                modelo.setCdModelo(resultado.getInt("cdModelo"));
                bicicleta.setPreco(resultado.getDouble("preco"));
                bicicleta.setQuantidade(resultado.getInt("quantidade"));
                
                //Obtendo os dados completos das Marcas e Modelos associada a Bicicleta
                MarcaDAO marcaDAO = new MarcaDAO();
                marcaDAO.setConnection(connection);
                marca = marcaDAO.buscar(marca);
                
                ModeloDAO modeloDAO = new ModeloDAO();
                modeloDAO.setConnection(connection);
                modelo = modeloDAO.buscar(modelo);
                
                bicicleta.setMarca(marca);
                bicicleta.setModelo(modelo);
                retorno.add(bicicleta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Bicicleta buscar(Bicicleta bicicleta) {
        String sql = "SELECT * FROM bicicletas WHERE cdBicicleta=?";
        Bicicleta retorno = new Bicicleta();
        Marca marca = new Marca();
        Modelo modelo = new Modelo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bicicleta.getCdBicicleta());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno.setCdBicicleta(resultado.getInt("cdBicicleta"));
                retorno.setNome(resultado.getString("nome"));
                marca.setCdMarca(resultado.getInt("cdMarca"));
                modelo.setCdModelo(resultado.getInt("cdModelo"));
                retorno.setPreco(resultado.getDouble("preco"));
                retorno.setQuantidade(resultado.getInt("quantidade"));
                retorno.setMarca(marca);
                retorno.setModelo(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
