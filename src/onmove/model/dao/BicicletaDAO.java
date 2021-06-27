package onmove.model.dao;
//package outros.model.dao;

import onmove.model.dao.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import onmove.model.domain.Modelo;
import onmove.model.domain.Bicicleta;

public class BicicletaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Bicicleta bicicleta) {
        String sql = "INSERT INTO bicicletas(nome, preco, quantidade, cdModelo) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, bicicleta.getNome());
            stmt.setDouble(2, bicicleta.getPreco());
            stmt.setInt(3, bicicleta.getQuantidade());
            stmt.setInt(4, bicicleta.getModelo().getCdModelo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Bicicleta bicicleta) {
        String sql = "UPDATE produtos SET nome=?, preco=?, quantidade=?, cdModelo=? WHERE cdBicicleta=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, bicicleta.getNome());
            stmt.setDouble(2, bicicleta.getPreco());
            stmt.setInt(3, bicicleta.getQuantidade());
            stmt.setInt(4, bicicleta.getModelo().getCdModelo());
            stmt.setInt(5, bicicleta.getModelo().getCdModelo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Bicicleta bicicleta) {
        String sql = "DELETE FROM produtos WHERE cdCliente=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bicicleta.getCdBicicleta());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
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
                Modelo modelo = new Modelo();
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                bicicleta.setNome(resultado.getString("nome"));
                bicicleta.setPreco(resultado.getDouble("preco"));
                bicicleta.setQuantidade(resultado.getInt("quantidade"));
                modelo.setCdModelo(resultado.getInt("cdModelo"));
                
                //Obtendo os dados completos da Categoria associada ao Produto
                ModeloDAO modeloDAO = new ModeloDAO();
                modeloDAO.setConnection(connection);
                modelo = modeloDAO.buscar(modelo);
                
                bicicleta.setModelo(modelo);
                retorno.add(bicicleta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Bicicleta> listarPorModelo(Modelo modelo) {
        String sql = "SELECT * FROM bicicletas WHERE cdModelo=?";
        List<Bicicleta> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, modelo.getCdModelo());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Bicicleta bicicleta = new Bicicleta();
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                bicicleta.setNome(resultado.getString("nome"));
                bicicleta.setPreco(resultado.getDouble("preco"));
                bicicleta.setQuantidade(resultado.getInt("quantidade"));
                modelo.setCdModelo(resultado.getInt("cdModelo"));
                bicicleta.setModelo(modelo);
                retorno.add(bicicleta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Bicicleta buscar(Bicicleta bicicleta) {
        String sql = "SELECT * FROM produtos WHERE cdBicicleta=?";
        Bicicleta retorno = new Bicicleta();
        Modelo modelo = new Modelo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bicicleta.getCdBicicleta());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno.setCdBicicleta(resultado.getInt("cdBicicleta"));
                retorno.setNome(resultado.getString("nome"));
                retorno.setPreco(resultado.getDouble("preco"));
                retorno.setQuantidade(resultado.getInt("quantidade"));
                modelo.setCdModelo(resultado.getInt("cdModelo"));
                retorno.setModelo(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
