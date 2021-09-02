package onmove.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import onmove.model.domain.ItemDeDevolucao;
import onmove.model.domain.Bicicleta;
import onmove.model.domain.Devolucao;

public class ItemDeDevolucaoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(ItemDeDevolucao itemDeDevolucao) {
        String sql = "INSERT INTO itensdedevolucao(quantidade, cdBicicleta, cdDevolucao) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeDevolucao.getQuantidade());
            stmt.setInt(2, itemDeDevolucao.getBicicleta().getCdBicicleta());
            stmt.setInt(3, itemDeDevolucao.getDevolucao().getCdDevolucao());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeDevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(ItemDeDevolucao itemDeDevolucao) {
        return true;
    }

    public boolean remover(ItemDeDevolucao itemDeDevolucao) {
        String sql = "DELETE FROM itensdedevolucao WHERE cdItemDeDevolucao=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeDevolucao.getCdItemDeDevolucao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeDevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<ItemDeDevolucao> listar() {
        String sql = "SELECT * FROM itensDeDevolucao";
        List<ItemDeDevolucao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemDeDevolucao itemDeDevolucao = new ItemDeDevolucao();
                Bicicleta bicicleta = new Bicicleta();
                Devolucao devolucao = new Devolucao();
                itemDeDevolucao.setCdItemDeDevolucao(resultado.getInt("cdItemDeDevolucao"));
                itemDeDevolucao.setQuantidade(resultado.getInt("quantidade"));
                itemDeDevolucao.setValor(resultado.getDouble("valor"));
                
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                devolucao.setCdDevolucao(resultado.getInt("cdDevolucao"));
                
                //Obtendo os dados completos do Bicicleta associado ao Item de Venda
                BicicletaDAO bicicletaDAO = new BicicletaDAO();
                bicicletaDAO.setConnection(connection);
                bicicleta = bicicletaDAO.buscar(bicicleta);
                
                DevolucaoDAO devolucaoDAO = new DevolucaoDAO();
                devolucaoDAO.setConnection(connection);
                devolucao = devolucaoDAO.buscar(devolucao);
                
                itemDeDevolucao.setBicicleta(bicicleta);
                itemDeDevolucao.setDevolucao(devolucao);
                
                retorno.add(itemDeDevolucao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeDevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<ItemDeDevolucao> listarPorDevolucao(Devolucao devolucao) {
        String sql = "SELECT * FROM itensDeDevolucao WHERE cdDevolucao=?";
        List<ItemDeDevolucao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, devolucao.getCdDevolucao());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemDeDevolucao itemDeDevolucao = new ItemDeDevolucao();
                Bicicleta bicicleta = new Bicicleta();
                Devolucao a = new Devolucao();
                itemDeDevolucao.setCdItemDeDevolucao(resultado.getInt("cdItemDeDevolucao"));
                itemDeDevolucao.setQuantidade(resultado.getInt("quantidade"));
                
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                a.setCdDevolucao(resultado.getInt("cdDevolucao"));
                
                //Obtendo os dados completos do Bicicleta associado ao Item de Venda
                BicicletaDAO bicicletaDAO = new BicicletaDAO();
                bicicletaDAO.setConnection(connection);
                bicicleta = bicicletaDAO.buscar(bicicleta);
                
                itemDeDevolucao.setBicicleta(bicicleta);
                itemDeDevolucao.setDevolucao(a);
                
                retorno.add(itemDeDevolucao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeDevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public ItemDeDevolucao buscar(ItemDeDevolucao itemDeDevolucao) {
        String sql = "SELECT * FROM itensDeAluguel WHERE cdItemDeAluguel=?";
        ItemDeDevolucao retorno = new ItemDeDevolucao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeDevolucao.getCdItemDeDevolucao());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Bicicleta bicicleta = new Bicicleta();
                Devolucao devolucao = new Devolucao();
                itemDeDevolucao.setCdItemDeDevolucao(resultado.getInt("cdItemDeDevolucao"));
                itemDeDevolucao.setQuantidade(resultado.getInt("quantidade"));
                itemDeDevolucao.setValor(resultado.getDouble("valor"));
                
                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));
                devolucao.setCdDevolucao(resultado.getInt("cddevolucao"));
                
                //Obtendo os dados completos do Cliente associado à Venda
                BicicletaDAO bicicletaDAO = new BicicletaDAO();
                bicicletaDAO.setConnection(connection);
                bicicleta = bicicletaDAO.buscar(bicicleta);
                
                DevolucaoDAO devolucaoDAO = new DevolucaoDAO();
                devolucaoDAO.setConnection(connection);
                devolucao = devolucaoDAO.buscar(devolucao);
                
                itemDeDevolucao.setBicicleta(bicicleta);
                itemDeDevolucao.setDevolucao(devolucao);
                
                retorno = itemDeDevolucao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
