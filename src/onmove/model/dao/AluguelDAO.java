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
        String sql = "INSERT INTO alugueis (data_emprestimo, data_devolucao, valor, pago, cdCliente) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(aluguel.getDataEmprestimo()));
            stmt.setDate(2, Date.valueOf(aluguel.getDataDevolucao()));
            stmt.setDouble(3, aluguel.getValor());
            stmt.setBoolean(4, aluguel.getPago());
            stmt.setInt(5, aluguel.getCliente().getCdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Aluguel aluguel) {
        String sql = "UPDATE alugueis SET data_emprestimo=?, data_devolucao=?, valor=?, pago=?, cdCliente=? WHERE cdAluguel=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(aluguel.getDataEmprestimo()));
            stmt.setDate(2, Date.valueOf(aluguel.getDataDevolucao()));
            stmt.setDouble(3, aluguel.getValor());
            stmt.setBoolean(4, aluguel.getPago());
            stmt.setInt(5, aluguel.getCliente().getCdCliente());
            stmt.setInt(6, aluguel.getCdAluguel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Aluguel aluguel) {
        String sql = "DELETE FROM alugueis WHERE cdAluguel=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluguel.getCdAluguel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não foi possível remover aluguel!");
            alert.show();
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Aluguel> listar() {
        String sql = "SELECT * FROM alugueis";
        List<Aluguel> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Aluguel aluguel = new Aluguel();
                Cliente cliente = new Cliente();
                List<ItemDeAluguel> itensDeAluguel = new ArrayList();

                aluguel.setCdAluguel(resultado.getInt("cdAluguel"));
                aluguel.setDataEmprestimo(resultado.getDate("data_emprestimo").toLocalDate());
                aluguel.setDataDevolucao(resultado.getDate("data_devolucao").toLocalDate());
                aluguel.setValor(resultado.getDouble("valor"));
                aluguel.setPago(resultado.getBoolean("pago"));
                cliente.setCdCliente(resultado.getInt("cdCliente"));

                //Obtendo os dados completos do Cliente associado à Aluguel
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                cliente = clienteDAO.buscar(cliente);

                //Obtendo os dados completos dos Itens de Venda associados à Aluguel
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
        String sql = "SELECT * FROM alugueis WHERE cdAluguel=?";
        Aluguel retorno = new Aluguel();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluguel.getCdAluguel());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cliente cliente = new Cliente();
                aluguel.setCdAluguel(resultado.getInt("cdAluguel"));
                aluguel.setDataEmprestimo(resultado.getDate("data_emprestimo").toLocalDate());
                aluguel.setValor(resultado.getDouble("valor"));
                aluguel.setPago(resultado.getBoolean("pago"));
                cliente.setCdCliente(resultado.getInt("cdCliente"));
                
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                cliente = clienteDAO.buscar(cliente);
                
                aluguel.setCliente(cliente);
                retorno = aluguel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Aluguel buscarUltimoAluguel() {
        String sql = "SELECT max(cdAluguel) FROM alugueis";
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
        String sql = "select count(cdAluguel), extract(year from data_emprestimo) as ano, extract(month from data_emprestimo) as mes from alugueis group by ano, mes order by ano, mes";
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
    
    
     public  Aluguel buscarPorCliente(Cliente cliente) {
        String sql = "SELECT * FROM alugueis WHERE alugueis.cdCliente=?";
        Aluguel retorno = new Aluguel();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getCdCliente());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Aluguel aluguel = new Aluguel();
                ItemDeAluguel itemDeAluguel = new ItemDeAluguel();
                List<ItemDeAluguel> itensDeAluguel = new ArrayList();
                
                aluguel.setCdAluguel(resultado.getInt("cdAluguel"));
                aluguel.setDataDevolucao(resultado.getDate("data_devolucao").toLocalDate());
                itemDeAluguel.setValor(resultado.getDouble("valor"));
//                itemDeAluguel.setQuantidade(resultado.getInt("quantidade"));
                aluguel.setPago(resultado.getBoolean("pago"));
                System.out.println(aluguel.getCdAluguel());
                System.out.println(cliente.getNome());
                System.out.println(aluguel.getDataDevolucao());
                
                //Obtendo os dados completos dos Itens de Venda associados à Aluguel
                ItemDeAluguelDAO itemDeAluguelDAO = new ItemDeAluguelDAO();
                itemDeAluguelDAO.setConnection(connection);
                itensDeAluguel = itemDeAluguelDAO.listarPorAluguel(aluguel);
                
                System.out.println(itemDeAluguelDAO.listarPorAluguel(aluguel));
                aluguel.setCliente(cliente);
//                aluguel.setItensDeAluguel(itensDeAluguel);
                retorno = aluguel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
