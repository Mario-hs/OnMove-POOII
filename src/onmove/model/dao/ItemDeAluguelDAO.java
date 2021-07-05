package onmove.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import onmove.model.domain.ItemDeAluguel;
import onmove.model.domain.Bicicleta;
import onmove.model.domain.Aluguel;

public class ItemDeAluguelDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(ItemDeAluguel itemDeAluguel) {
        String sql = "INSERT INTO itensdealuguel(quantidade, valor, cdBicicleta, cdAluguel) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeAluguel.getQuantidade());
            stmt.setDouble(2, itemDeAluguel.getValor());
            stmt.setInt(3, itemDeAluguel.getBicicleta().getCdBicicleta());
            stmt.setInt(4, itemDeAluguel.getAluguel().getCdAluguel());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeAluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(ItemDeAluguel itemDeAluguel) {
        return true;
    }

    public boolean remover(ItemDeAluguel itemDeAluguel) {
        String sql = "DELETE FROM itensDeAluguel WHERE cdItemDeAluguel=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeAluguel.getCdItemDeAluguel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeAluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<ItemDeAluguel> listar() {
        String sql = "SELECT * FROM itensDeAluguel";
        List<ItemDeAluguel> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemDeAluguel itemDeAluguel = new ItemDeAluguel();
                Bicicleta bicicleta = new Bicicleta();
                Aluguel aluguel = new Aluguel();
                itemDeAluguel.setCdItemDeAluguel(resultado.getInt("cdItemDeAluguel"));
                itemDeAluguel.setQuantidade(resultado.getInt("quantidade"));
                itemDeAluguel.setValor(resultado.getDouble("valor"));
                
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                aluguel.setCdAluguel(resultado.getInt("cdAluguel"));
                
                //Obtendo os dados completos do Bicicleta associado ao Item de Venda
                BicicletaDAO bicicletaDAO = new BicicletaDAO();
                bicicletaDAO.setConnection(connection);
                bicicleta = bicicletaDAO.buscar(bicicleta);
                
                AluguelDAO aluguelDAO = new AluguelDAO();
                aluguelDAO.setConnection(connection);
                aluguel = aluguelDAO.buscar(aluguel);
                
                itemDeAluguel.setBicicleta(bicicleta);
                itemDeAluguel.setAluguel(aluguel);
                
                retorno.add(itemDeAluguel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeAluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<ItemDeAluguel> listarPorAluguel(Aluguel aluguel) {
        String sql = "SELECT * FROM itensDeAluguel WHERE cdAluguel=?";
        List<ItemDeAluguel> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluguel.getCdAluguel());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemDeAluguel itemDeAluguel = new ItemDeAluguel();
                Bicicleta bicicleta = new Bicicleta();
                Aluguel a = new Aluguel();
                itemDeAluguel.setCdItemDeAluguel(resultado.getInt("cdItemDeAluguel"));
                itemDeAluguel.setQuantidade(resultado.getInt("quantidade"));
                itemDeAluguel.setValor(resultado.getDouble("valor"));
                
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                a.setCdAluguel(resultado.getInt("cdAluguel"));
                
                //Obtendo os dados completos do Bicicleta associado ao Item de Venda
                BicicletaDAO bicicletaDAO = new BicicletaDAO();
                bicicletaDAO.setConnection(connection);
                bicicleta = bicicletaDAO.buscar(bicicleta);
                
                itemDeAluguel.setBicicleta(bicicleta);
                itemDeAluguel.setAluguel(a);
                
                retorno.add(itemDeAluguel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeAluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public ItemDeAluguel buscar(ItemDeAluguel itemDeAluguel) {
        String sql = "SELECT * FROM itensDeAluguel WHERE cdItemDeAluguel=?";
        ItemDeAluguel retorno = new ItemDeAluguel();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeAluguel.getCdItemDeAluguel());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Bicicleta bicicleta = new Bicicleta();
                Aluguel aluguel = new Aluguel();
                itemDeAluguel.setCdItemDeAluguel(resultado.getInt("cdItemDeAluguel"));
                itemDeAluguel.setQuantidade(resultado.getInt("quantidade"));
                itemDeAluguel.setValor(resultado.getDouble("valor"));
                
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                aluguel.setCdAluguel(resultado.getInt("cdAluguel"));
                
                //Obtendo os dados completos do Cliente associado à Venda
                BicicletaDAO bicicletaDAO = new BicicletaDAO();
                bicicletaDAO.setConnection(connection);
                bicicleta = bicicletaDAO.buscar(bicicleta);
                
                AluguelDAO aluguelDAO = new AluguelDAO();
                aluguelDAO.setConnection(connection);
                aluguel = aluguelDAO.buscar(aluguel);
                
                itemDeAluguel.setBicicleta(bicicleta);
                itemDeAluguel.setAluguel(aluguel);
                
                retorno = itemDeAluguel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
