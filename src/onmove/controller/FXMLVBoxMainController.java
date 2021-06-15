package onmove.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;


public class FXMLVBoxMainController implements Initializable {

    @FXML
    private MenuItem menuItemCadastrosClientes;
    
    @FXML
    private MenuItem menuItemCadastrosCategoria;
    
    @FXML
    private MenuItem menuItemCadastrosProdutos;
    
    @FXML
    private MenuItem menuItemProcessosVendas;
    
    @FXML
    private MenuItem menuItemGraficosVendasPorMes;
    
    @FXML
    private MenuItem menuItemRelatorioQuantidadeProdutosEstoque;
    
    @FXML
    private AnchorPane anchorPane;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    public void handleMenuItemCadastrosClientes() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneCadastrosClientes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemCadastrosCategoria() {
        //
    }
    
    @FXML
    public void handleMenuItemCadastrosProdutos() {
        //
    }
    
    @FXML
    public void handleMenuItemProcessosVendas() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneProcessosVendas.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGraficosVendasPorMes() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneGraficosVendasPorMes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatoriosQuantidadeProdutos() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneRelatoriosQuantidadeProdutos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
}