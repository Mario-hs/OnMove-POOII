package onmove.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import onmove.Main;

public class FXMLHomeController implements Initializable {
    @FXML
    private MenuItem cadastrosClientes;
    
    @FXML
    private MenuItem cadastrosBicicletas;
    
    @FXML
    private MenuItem processosAluguel;
    
    @FXML
    private MenuItem menuItemProcessosDevolucao;
    
    @FXML
    private MenuItem menuItemProcessosMulta;
    
    @FXML
    private MenuItem menuItemGraficosAluguelPorMes;
    
    @FXML
    private MenuItem menuItemGraficosAluguelPorCliente;
    
    @FXML
    private MenuItem menuItemRelatorioQuantidadeBicicletasEstoque;
    
    @FXML
    private MenuItem menuItemRelatorioQuantidadeBicicletasAlugadas;
        
    @FXML
    private MenuItem menuItemRelatorioQuantidadeBicicletasDisponiveis;
    
    @FXML
    private MenuItem menuItemEquipe;
    
    @FXML
    private Button btnSair;
   
    @FXML
    private AnchorPane anchorPane;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSair.setOnMouseClicked((MouseEvent e)-> {
            close();
        });
        btnSair.setOnKeyPressed((KeyEvent e)-> {
            if(e.getCode() == KeyCode.ENTER){
                close();
            }
        });
        
    }  
    
    public void close(){
        Main.getStage().close();
    }
    
    public void selectionScreen(String opcao) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLHeaderController.class.getResource("/onmove/view/FXMLHeader.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("ON MOVE - Alugues de Bicicletas");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        FXMLHeaderController controller = loader.getController();
        controller.handleScreen(opcao);
        dialogStage.showAndWait();
    }
    
    @FXML
    public void handleCadastrosClientes() throws IOException{
        selectionScreen("clientes");
    }
    
    @FXML
    public void handleCadastrosBicicletas() throws IOException {
        selectionScreen("bicicletas");
    }
    
    @FXML
    public void handleProcessosAluguel() throws IOException{
        selectionScreen("alugueis");
    }
    
    @FXML
    public void handleProcessosDevolucao() throws IOException{
        selectionScreen("devolucoes");
    }
    
    @FXML
    public void handleProcessosMulta() throws IOException{
        selectionScreen("multas");
    }
    
    @FXML
    public void handleGraficosAluguelPorMes() throws IOException{
        selectionScreen("graficoPorMes");
    } 
    
    @FXML
    public void handleGraficosAluguelPorCliente() throws IOException{
        selectionScreen("graficoPorCliente");
    }
    
    @FXML
    public void handleRelatoriosQuantidadeBicicletasAlugadas() throws IOException{
        selectionScreen("bicicletasAlugadas");
    }
    
    @FXML
    public void handleRelatoriosQuantidadeBicicletasDisponiveis() throws IOException{
        selectionScreen("bicicletasDisponiveis");
    }
     
    @FXML
    public void handleRelatoriosQuantidadeBicicletasCadastradas() throws IOException{
        selectionScreen("bicicletasCadastradas");
    }
    
    @FXML
    public void handleEquipe() throws IOException{
        selectionScreen("equipe");
    }
}
