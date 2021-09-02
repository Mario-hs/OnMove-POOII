package onmove.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import onmove.model.domain.Aluguel;
import onmove.model.domain.Bicicleta;
import onmove.model.domain.Cliente;
//import onmove.model.domain.ItemDeDevolucao;
import onmove.model.domain.Devolucao;
import onmove.model.domain.ItemDeAluguel;
import onmove.model.domain.ItemDeDevolucao;

public class DevolucaoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Devolucao devolucao) {
        String sql = "INSERT INTO devolucoes (data_devolucao, valor, pago, cdCliente) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(devolucao.getDataDevolucao()));
            stmt.setDouble(2, devolucao.getValor());
            stmt.setBoolean(3, devolucao.getPago());
            stmt.setInt(4, devolucao.getCliente().getCdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Devolucao devolucao) {
        String sql = "UPDATE devolucoes SET data_devolucao=?, valor=?, pago=?, cdCliente=? WHERE cdDevolucao=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(devolucao.getDataDevolucao()));
            stmt.setDouble(2, devolucao.getValor());
            stmt.setBoolean(3, devolucao.getPago());
            stmt.setInt(4, devolucao.getCliente().getCdCliente());
            stmt.setInt(6, devolucao.getCdDevolucao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Devolucao devolucao) {
        String sql = "DELETE FROM devolucoes WHERE cdDevolucao=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, devolucao.getCdDevolucao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não foi possível remover devolucao!");
            alert.show();
            Logger.getLogger(DevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Devolucao> listar() {
//        String sql = "SELECT * FROM devolucoes, bicicletas WHERE bicicletas.cdBicicleta = devolucoes.cdBicicleta";
//        String sql = "SELECT * FROM devolucoes, itensdedevolucao, clientes, bicicletas WHERE devolucoes.cddevolucao = itensdedevolucao.cddevolucao AND   itensdedevolucao.cdbicicleta = bicicletas.cdbicicleta AND   devolucoes.cdcliente = clientes.cdcliente";
        String sql = "SELECT * FROM devolucoes";
        List<Devolucao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Devolucao devolucao = new Devolucao();
                Cliente cliente = new Cliente();
                Bicicleta bicicleta = new Bicicleta();
                List<ItemDeDevolucao> itensDeDevolucao = new ArrayList();

                devolucao.setCdDevolucao(resultado.getInt("cdDevolucao"));
                devolucao.setDataDevolucao(resultado.getDate("data_devolucao").toLocalDate());
//                devolucao.setValor(resultado.getDouble("valor"));
                devolucao.setPago(resultado.getBoolean("pago"));
                cliente.setCdCliente(resultado.getInt("cdCliente"));
//                bicicleta.setCdBicicleta(resultado.getInt("cdBicicleta"));


                //Obtendo os dados completos do Cliente associado à Devolucao
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                cliente = clienteDAO.buscar(cliente);
                
                BicicletaDAO bicicletaDAO = new BicicletaDAO();
                bicicletaDAO.setConnection(connection);
                bicicleta = bicicletaDAO.buscar(bicicleta);

                //Obtendo os dados completos dos Itens de Venda associados à Venda
                ItemDeDevolucaoDAO itemDeDevolucaoDAO = new ItemDeDevolucaoDAO();
                itemDeDevolucaoDAO.setConnection(connection);
                itensDeDevolucao = itemDeDevolucaoDAO.listarPorDevolucao(devolucao);

                devolucao.setCliente(cliente);
                devolucao.setItensDeDevolucao(itensDeDevolucao);
                retorno.add(devolucao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Devolucao buscar(Devolucao devolucao) {
        String sql = "SELECT * FROM devolucoes WHERE cdDevolucao=?";
        Devolucao retorno = new Devolucao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, devolucao.getCdDevolucao());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cliente cliente = new Cliente();
                Bicicleta bicicleta = new Bicicleta();
                devolucao.setCdDevolucao(resultado.getInt("cdDevolucao"));
                devolucao.setDataDevolucao(resultado.getDate("data_devolucao").toLocalDate());
                devolucao.setValor(resultado.getDouble("valor"));
                devolucao.setPago(resultado.getBoolean("pago"));
                cliente.setCdCliente(resultado.getInt("cdCliente"));
                devolucao.setCliente(cliente);
                retorno = devolucao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Devolucao buscarUltimoDevolucao() {
        String sql = "SELECT max(cdDevolucao) FROM devolucoes";
        Devolucao retorno = new Devolucao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retorno.setCdDevolucao(resultado.getInt("max"));
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Map<Integer, ArrayList> listarQuantidadeDevolucaoPorMes() {
        String sql = "select count(cdDevolucao), extract(year from data_emprestimo) as ano, extract(month from data_emprestimo) as mes from devolucoes group by ano, mes order by ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(resultado.getInt("ano")))
                {
                    linha.add(resultado.getInt("mes"));
                    linha.add(resultado.getInt("count"));
                    retorno.put(resultado.getInt("ano"), linha);
                }else{
                    ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
                    linhaNova.add(resultado.getInt("mes"));
                    linhaNova.add(resultado.getInt("count"));
                }
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
