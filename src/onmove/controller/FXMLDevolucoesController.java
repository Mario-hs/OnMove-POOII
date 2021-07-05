package onmove.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import onmove.model.dao.BicicletaDAO;
import onmove.model.dao.ClienteDAO;
import onmove.model.dao.DevolucaoDAO;
import onmove.model.dao.ItemDeDevolucaoDAO;
import onmove.model.database.Database;
import onmove.model.database.DatabaseFactory;
import onmove.model.domain.Bicicleta;
import onmove.model.domain.Devolucao;
import onmove.model.domain.ItemDeDevolucao;

public class FXMLDevolucoesController implements Initializable {
    @FXML
    private TableView<Devolucao> tableViewDevolucoes;
    @FXML
    private TableColumn<Devolucao, Integer> tableColumnCodigo;
    @FXML
    private TableColumn<Devolucao, String> tableColumnDataDevolucao;
    @FXML
    private TableColumn<Devolucao, String> tableColumnCliente;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonDetalhes;
    
    private List<Devolucao> listDevolucoes;
    private ObservableList<Devolucao> observableListDevolucoes;
    
    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final DevolucaoDAO devolucaoDAO = new DevolucaoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ItemDeDevolucaoDAO itemDeDevolucaoDAO = new ItemDeDevolucaoDAO();
    private final BicicletaDAO bicicletaDAO = new BicicletaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        devolucaoDAO.setConnection(connection);
        carregarTableViewDevolucoes();
    }    
    
    public void carregarTableViewDevolucoes() {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("cdDevolucao"));
        tableColumnDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        listDevolucoes = devolucaoDAO.listar();

        observableListDevolucoes = FXCollections.observableArrayList(listDevolucoes);
        tableViewDevolucoes.setItems(observableListDevolucoes);
    }
    
    @FXML
    public void handleButtonInserir() throws IOException {
        Devolucao devolucao = new Devolucao();
        List<ItemDeDevolucao> listItensDeDevolucao = new ArrayList<>();
        devolucao.setItensDeDevolucao(listItensDeDevolucao);
        boolean buttonConfirmarClicked = showFXMLCadastrarDevolucoes(devolucao);
        
        if (buttonConfirmarClicked) {
            try {
                connection.setAutoCommit(false);
                devolucaoDAO.setConnection(connection);
                devolucaoDAO.inserir(devolucao);
                itemDeDevolucaoDAO.setConnection(connection);
                bicicletaDAO.setConnection(connection);
                for (ItemDeDevolucao listItemDeDevolucao : devolucao.getItensDeDevolucao()) {
                    Bicicleta bicicleta = listItemDeDevolucao.getBicicleta();
                    listItemDeDevolucao.setDevolucao(devolucaoDAO.buscarUltimoDevolucao());
                    itemDeDevolucaoDAO.inserir(listItemDeDevolucao);
                    bicicleta.setQuantidade(bicicleta.getQuantidade() + listItemDeDevolucao.getQuantidade());
                    bicicletaDAO.alterar(bicicleta);
                }
                connection.commit();
                carregarTableViewDevolucoes();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(FXMLAlugueisController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(FXMLAlugueisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Devolucao devolucao = tableViewDevolucoes.getSelectionModel().getSelectedItem();
        if (devolucao != null) {
            boolean buttonConfirmarClicked = showFXMLCadastrarDevolucoes(devolucao);
            if (buttonConfirmarClicked) {
                devolucaoDAO.alterar(devolucao);
                carregarTableViewDevolucoes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException, SQLException {
        Devolucao devolucao = tableViewDevolucoes.getSelectionModel().getSelectedItem();
        if (devolucao != null) {
            connection.setAutoCommit(false);
            devolucaoDAO.setConnection(connection);
            itemDeDevolucaoDAO.setConnection(connection);
            bicicletaDAO.setConnection(connection);
            for (ItemDeDevolucao listItemDeDevolucao : devolucao.getItensDeDevolucao()) {
                Bicicleta bicicleta = listItemDeDevolucao.getBicicleta();
                bicicleta.setQuantidade(bicicleta.getQuantidade() + listItemDeDevolucao.getQuantidade());
                bicicletaDAO.alterar(bicicleta);
                itemDeDevolucaoDAO.remover(listItemDeDevolucao);
            }
            devolucaoDAO.remover(devolucao);
            connection.commit();
            carregarTableViewDevolucoes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma devolucao na Tabela!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonDetalhes() throws IOException {
        Devolucao devolucao = tableViewDevolucoes.getSelectionModel().getSelectedItem();
        if (devolucao != null) {
            boolean buttonConfirmarClicked = showFXMLDetalhesDevolucoes(devolucao);
            if (buttonConfirmarClicked) {
                devolucaoDAO.buscar(devolucao);
                carregarTableViewDevolucoes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }
    
    public boolean showFXMLCadastrarDevolucoes(Devolucao devolucao) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastrarDevolucoesController.class.getResource("/onmove/view/FXMLCadastrarDevolucoes.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Devoluções");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLCadastrarDevolucoesController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setDevolucao(devolucao);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
    }

    public boolean showFXMLDetalhesDevolucoes(Devolucao devolucao) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLDetalhesDevolucaoController.class.getResource("/onmove/view/FXMLDetalhesDevolucao.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Detalhes da Devolução");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLDetalhesDevolucaoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setDevolucao(devolucao);
        
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
    }
}
