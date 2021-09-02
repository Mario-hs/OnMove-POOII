package onmove.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import onmove.model.dao.BicicletaDAO;
import onmove.model.database.Database;
import onmove.model.database.DatabaseFactory;
import onmove.model.domain.Bicicleta;

public class FXMLBicicletasController implements Initializable {
    @FXML
    private TableView<Bicicleta> tableViewBicicletas;
    @FXML
    private TableColumn<Bicicleta, String> tableColumnBicicletaNome;
    @FXML
    private TableColumn<Bicicleta, String> tableColumnBicicletaPreco;
    @FXML
    private TableColumn<Bicicleta, String> tableColumnBicicletaQuantidade;
            
    private List<Bicicleta> listBicicletas;
    private ObservableList<Bicicleta> observableListBicicletas;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final BicicletaDAO bicicletaDAO = new BicicletaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bicicletaDAO.setConnection(connection);
        carregarTableViewBicicleta();
    }    
    
    public void carregarTableViewBicicleta() {
        tableColumnBicicletaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnBicicletaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnBicicletaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        listBicicletas = bicicletaDAO.listar();

        observableListBicicletas = FXCollections.observableArrayList(listBicicletas);
        tableViewBicicletas.setItems(observableListBicicletas);
    }
    
    
        @FXML
    public void handleButtonInserir() throws IOException {
        Bicicleta bicicleta = new Bicicleta();
        boolean buttonConfirmarClicked = showFXMLCadastrarBicicletas(bicicleta);
        if (buttonConfirmarClicked) {
            bicicletaDAO.inserir(bicicleta);
            carregarTableViewBicicleta();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Bicicleta bicicleta = tableViewBicicletas.getSelectionModel().getSelectedItem();
        if (bicicleta != null) {
            boolean buttonConfirmarClicked = showFXMLCadastrarBicicletas(bicicleta);
            if (buttonConfirmarClicked) {
                bicicletaDAO.alterar(bicicleta);
                carregarTableViewBicicleta();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma bicicleta na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Bicicleta bicicleta = tableViewBicicletas.getSelectionModel().getSelectedItem();
        if (bicicleta != null) {
            bicicletaDAO.remover(bicicleta);
            carregarTableViewBicicleta();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonDetalhes() throws IOException {
        Bicicleta bicicleta = tableViewBicicletas.getSelectionModel().getSelectedItem();
        if (bicicleta != null) {
            boolean buttonConfirmarClicked = showFXMLDetalhesBicicleta(bicicleta);
            if (buttonConfirmarClicked) {
                bicicletaDAO.buscar(bicicleta);
                carregarTableViewBicicleta();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma bicicleta na Tabela!");
            alert.show();
        }
    }
    
    public boolean showFXMLCadastrarBicicletas(Bicicleta bicicleta) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastrarBicicletasController.class.getResource("/onmove/view/FXMLCadastrarBicicletas.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastrar Bicicletas");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLCadastrarBicicletasController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setBicicleta(bicicleta);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

    public boolean showFXMLDetalhesBicicleta(Bicicleta bicicleta) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLDetalhesBicicletaController.class.getResource("/onmove/view/FXMLDetalhesBicicleta.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Detalhes da Bicicleta");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLDetalhesBicicletaController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setBicicleta(bicicleta);
        
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
    }

    
}
