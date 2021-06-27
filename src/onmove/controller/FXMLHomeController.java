package onmove.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private MenuItem menuItemGraficosAluguelPorMes;
    
    @FXML
    private MenuItem menuItemGraficosAluguelPorAno;
    
    @FXML
    private MenuItem menuItemGraficosAluguelPorCliente;
    
    @FXML
    private MenuItem menuItemRelatorioQuantidadeBicicletasEstoque;
    
    @FXML
    private MenuItem menuItemRelatorioQuantidadeBicicletasAlugadas;
        
    @FXML
    private MenuItem menuItemRelatorioQuantidadeBicicletasDisponiveis;
    
    @FXML
    private Button btnSair;
   
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private AnchorPane anchorPaneBody;
    
    @FXML
    private AnchorPane anchorPaneHeader;
    
    @FXML
    private AnchorPane anchorPaneButtons;
  
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
    
    @FXML
    public void handleCadastrosClientes() throws IOException{
        AnchorPane header = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLHeader.fxml"));
        anchorPane.getChildren().setAll(header);
    }
    
    @FXML
    public void handleCadastrosBicicletas() throws IOException {
        AnchorPane header = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLHeader.fxml"));
        anchorPane.getChildren().setAll(header);
    }
    
    @FXML
    public void handleProcessosAluguel() throws IOException{
        AnchorPane header = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLHeader.fxml"));
        anchorPane.getChildren().setAll(header);
    }
    
     @FXML
    public void handleMenuItemProcessosDevolucao() throws IOException{
        AnchorPane header = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLHeader.fxml"));
        anchorPane.getChildren().setAll(header);
    }
    
    
    @FXML
    public void handleMenuItemGraficosAluguelPorMes() throws IOException{
        AnchorPane header = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLHeader.fxml"));
        anchorPane.getChildren().setAll(header);
    } 

    @FXML
    public void handleMenuItemGraficosAluguelPorAno() throws IOException{
        AnchorPane header = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLHeader.fxml"));
        anchorPane.getChildren().setAll(header);
    }
    
    @FXML
    public void handleMenuItemGraficosAluguelPorCliente() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneGraficosVendasPorMes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatoriosQuantidadeBicicletas() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneRelatoriosQuantidadeProdutos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatoriosQuantidadeBicicletasAlugadas() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneRelatoriosQuantidadeProdutos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatoriosQuantidadeBicicletasDisponiveis() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneRelatoriosQuantidadeProdutos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    
}
