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
import onmove.model.dao.AluguelDAO;
import onmove.model.dao.BicicletaDAO;
import onmove.model.dao.ClienteDAO;
import onmove.model.dao.ItemDeAluguelDAO;
import onmove.model.database.Database;
import onmove.model.database.DatabaseFactory;
import onmove.model.domain.Aluguel;
import onmove.model.domain.Bicicleta;
import onmove.model.domain.ItemDeAluguel;

public class FXMLAlugueisController implements Initializable {
    @FXML
    private TableView<Aluguel> tableViewAlugueis;
    @FXML
    private TableColumn<Aluguel, Integer> tableColumnAluguelCodigo;
    @FXML
    private TableColumn<Aluguel, LocalDate> tableColumnAluguelData;
    @FXML
    private TableColumn<Aluguel, Aluguel> tableColumnAluguelCliente;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonDetalhes;
    
    private List<Aluguel> listAlugueis;
    private ObservableList<Aluguel> observableListAlugueis;
    
    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AluguelDAO aluguelDAO = new AluguelDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ItemDeAluguelDAO itemDeAluguelDAO = new ItemDeAluguelDAO();
    private final BicicletaDAO bicicletaDAO = new BicicletaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aluguelDAO.setConnection(connection);
        carregarTableViewAlugueis();
    }   
    
    public void carregarTableViewAlugueis() {
        tableColumnAluguelCodigo.setCellValueFactory(new PropertyValueFactory<>("cdAluguel"));
        tableColumnAluguelData.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        tableColumnAluguelCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        listAlugueis = aluguelDAO.listar();

        observableListAlugueis = FXCollections.observableArrayList(listAlugueis);
        tableViewAlugueis.setItems(observableListAlugueis);
    }
    
    @FXML
    public void handleButtonInserir() throws IOException {
        Aluguel aluguel = new Aluguel();
        List<ItemDeAluguel> listItensDeAluguel = new ArrayList<>();
        aluguel.setItensDeAluguel(listItensDeAluguel);
        boolean buttonConfirmarClicked = showFXMLCadastrarAlugueis(aluguel);
        
        if (buttonConfirmarClicked) {
            try {
                connection.setAutoCommit(false);
                aluguelDAO.setConnection(connection);
                aluguelDAO.inserir(aluguel);
                itemDeAluguelDAO.setConnection(connection);
                bicicletaDAO.setConnection(connection);
                for (ItemDeAluguel listItemDeAluguel : aluguel.getItensDeAluguel()) {
                    Bicicleta bicicleta = listItemDeAluguel.getBicicleta();
                    listItemDeAluguel.setAluguel(aluguelDAO.buscarUltimoAluguel());
                    itemDeAluguelDAO.inserir(listItemDeAluguel);
                    bicicleta.setQuantidade(bicicleta.getQuantidade() - listItemDeAluguel.getQuantidade());
                    bicicletaDAO.alterar(bicicleta);
                }
                connection.commit();
                carregarTableViewAlugueis();
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
        Aluguel aluguel = tableViewAlugueis.getSelectionModel().getSelectedItem();
        if (aluguel != null) {
            boolean buttonConfirmarClicked = showFXMLCadastrarAlugueis(aluguel);
            if (buttonConfirmarClicked) {
                aluguelDAO.alterar(aluguel);
                carregarTableViewAlugueis();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException, SQLException {
        Aluguel aluguel = tableViewAlugueis.getSelectionModel().getSelectedItem();
        if (aluguel != null) {
            connection.setAutoCommit(false);
            aluguelDAO.setConnection(connection);
            itemDeAluguelDAO.setConnection(connection);
            bicicletaDAO.setConnection(connection);
            for (ItemDeAluguel listItemDeAluguel : aluguel.getItensDeAluguel()) {
                Bicicleta bicicleta = listItemDeAluguel.getBicicleta();
                bicicleta.setQuantidade(bicicleta.getQuantidade() + listItemDeAluguel.getQuantidade());
                bicicletaDAO.alterar(bicicleta);
                itemDeAluguelDAO.remover(listItemDeAluguel);
            }
            aluguelDAO.remover(aluguel);
            connection.commit();
            carregarTableViewAlugueis();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma aluguel na Tabela!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonDetalhes() throws IOException {
        Aluguel aluguel = tableViewAlugueis.getSelectionModel().getSelectedItem();
        if (aluguel != null) {
            boolean buttonConfirmarClicked = showFXMLDetalhesAlugueis(aluguel);
            if (buttonConfirmarClicked) {
                aluguelDAO.buscar(aluguel);
                carregarTableViewAlugueis();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }
    
    public boolean showFXMLCadastrarAlugueis(Aluguel aluguel) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastrarAlugueisController.class.getResource("/onmove/view/FXMLCadastrarAlugueis.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Alugueis");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLCadastrarAlugueisController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAluguel(aluguel);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
    }

    public boolean showFXMLDetalhesAlugueis(Aluguel aluguel) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLDetalhesAluguelController.class.getResource("/onmove/view/FXMLDetalhesAluguel.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Detalhes do Aluguel");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLDetalhesAluguelController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAluguel(aluguel);
        
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
    }
    
}
