package onmove.model.dao;
//package outros.model.dao;

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
//import onmove.model.dao.ClienteDAO;
import onmove.model.domain.Cliente;
import onmove.model.domain.ItemDeAluguel;
import onmove.model.domain.Aluguel;

public class AluguelDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Aluguel aluguel) {
        String sql = "INSERT INTO aluguel(data, valor, pago, cdCliente) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(aluguel.getData()));
            stmt.setDouble(2, aluguel.getValor());
            stmt.setBoolean(3, aluguel.getPago());
            stmt.setInt(4, aluguel.getCliente().getCdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Aluguel aluguel) {
        String sql = "UPDATE clientes SET data=?, valor=?, pago=?, cdCliente=? WHERE cdAluguel=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(aluguel.getData()));
            stmt.setDouble(2, aluguel.getValor());
            stmt.setBoolean(3, aluguel.getPago());
            stmt.setInt(4, aluguel.getCliente().getCdCliente());
            stmt.setInt(5, aluguel.getCdAluguel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Aluguel aluguel) {
        String sql = "DELETE FROM vendas WHERE cdVenda=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluguel.getCdAluguel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Aluguel> listar() {
        String sql = "SELECT * FROM aluguel";
        List<Aluguel> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Aluguel aluguel = new Aluguel();
                Cliente cliente = new Cliente();
                List<ItemDeAluguel> itensDeAluguel = new ArrayList();

                aluguel.setCdAluguel(resultado.getInt("cdAluguel"));
                aluguel.setData(resultado.getDate("data").toLocalDate());
                aluguel.setValor(resultado.getDouble("valor"));
                aluguel.setPago(resultado.getBoolean("pago"));
                cliente.setCdCliente(resultado.getInt("cdCliente"));

                //Obtendo os dados completos do Cliente associado à Venda
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                cliente = clienteDAO.buscar(cliente);

                //Obtendo os dados completos dos Itens de Venda associados à Venda
                ItemDeAluguelDAO itemDeAluguelDAO = new ItemDeAluguelDAO();
                itemDeAluguelDAO.setConnection(connection);
                itensDeAluguel = itemDeAluguelDAO.listarPorAluguel(aluguel);

                aluguel.setCliente(cliente);
                aluguel.setItensDeAluguel(itensDeAluguel);
                retorno.add(aluguel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Aluguel buscar(Aluguel aluguel) {
        String sql = "SELECT * FROM aluguel WHERE cdAluguel=?";
        Aluguel retorno = new Aluguel();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluguel.getCdAluguel());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cliente cliente = new Cliente();
                aluguel.setCdAluguel(resultado.getInt("cdAluguel"));
                aluguel.setData(resultado.getDate("data").toLocalDate());
                aluguel.setValor(resultado.getDouble("valor"));
                aluguel.setPago(resultado.getBoolean("pago"));
                cliente.setCdCliente(resultado.getInt("cdCliente"));
                aluguel.setCliente(cliente);
                retorno = aluguel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Aluguel buscarUltimoAluguel() {
        String sql = "SELECT max(cdAluguel) FROM aluguel";
        Aluguel retorno = new Aluguel();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retorno.setCdAluguel(resultado.getInt("max"));
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Map<Integer, ArrayList> listarQuantidadeAluguelPorMes() {
        String sql = "select count(cdAluguel), extract(year from data) as ano, extract(month from data) as mes from aluguel group by ano, mes order by ano, mes";
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
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
