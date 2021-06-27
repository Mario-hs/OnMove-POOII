package onmove.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import onmove.model.domain.Cliente;

public class FXMLButtonsController implements Initializable {
     @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonDetalhes;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    public void handleButtonInserir() throws IOException {
//        Cliente cliente = new Cliente();
//        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
//        if (buttonConfirmarClicked) {
//            clienteDAO.inserir(cliente);
//            carregarTableViewCliente();
//        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
//        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
//        if (cliente != null) {
//            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
//            if (buttonConfirmarClicked) {
//                clienteDAO.alterar(cliente);
//                carregarTableViewCliente();
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Por favor, escolha um cliente na Tabela!");
//            alert.show();
//        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
//        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
//        if (cliente != null) {
//            clienteDAO.remover(cliente);
//            carregarTableViewCliente();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Por favor, escolha um cliente na Tabela!");
//            alert.show();
//        }
    }
    
    @FXML
    public void handleButtonDetalhes() throws IOException {
//        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
//        if (cliente != null) {
//            boolean buttonConfirmarClicked = showFXMLAnchorPaneDetalhesClientes(cliente);
//            if (buttonConfirmarClicked) {
//                clienteDAO.buscar(cliente);
//                carregarTableViewCliente();
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Por favor, escolha um cliente na Tabela!");
//            alert.show();
//        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosClientesDialog(Cliente cliente) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(FXMLAnchorPaneCadastrosClientesDialogController.class.getResource("/onmove/view/FXMLAnchorPaneCadastrosClientesDialog.fxml"));
//        AnchorPane page = (AnchorPane) loader.load();
//
//        // Criando um Estágio de Diálogo (Stage Dialog)
//        Stage dialogStage = new Stage();
//        dialogStage.setTitle("Cadastro de Clientes");
//        Scene scene = new Scene(page);
//        dialogStage.setScene(scene);
//
//        // Setando o cliente no Controller.
//        FXMLAnchorPaneCadastrosClientesDialogController controller = loader.getController();
//        controller.setDialogStage(dialogStage);
//        controller.setCliente(cliente);
//
//        // Mostra o Dialog e espera até que o usuário o feche
//        dialogStage.showAndWait();
//
//        return controller.isButtonConfirmarClicked();
        return false;
    }

    public boolean showFXMLAnchorPaneDetalhesClientes(Cliente cliente) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(FXMLAnchorPaneDetalhesClientesController.class.getResource("/onmove/view/FXMLAnchorPaneDetalhesClientes.fxml"));
//        AnchorPane page = (AnchorPane) loader.load();
//        
//        Stage dialogStage = new Stage();
//        dialogStage.setTitle("Detalhes do Cliente");
//        Scene scene = new Scene(page);
//        dialogStage.setScene(scene);
//
//        // Setando o cliente no Controller.
//        FXMLAnchorPaneDetalhesClientesController controller = loader.getController();
//        controller.setDialogStage(dialogStage);
//        controller.setCliente(cliente);
//        
//        // Mostra o Dialog e espera até que o usuário o feche
//        dialogStage.showAndWait();
//        
////        return controller.isButtonConfirmarClicked();
//        return true;
        return false;
    }
}
