package onmove.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FXMLHeaderController implements Initializable {

    @FXML
    private ImageView home;
    
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
    private AnchorPane anchorPane;
    
    @FXML
    private AnchorPane anchorPaneBody;
    
    @FXML
    private AnchorPane anchorPaneHeader;
    
    @FXML
    private AnchorPane anchorPaneButtons;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void handleHome() throws IOException{
        AnchorPane body = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLHome.fxml"));
        anchorPaneBody.getChildren().setAll(body);
    }

    @FXML
    public void handleCadastrosClientes() throws IOException{
        AnchorPane body = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLClientes.fxml"));
        anchorPaneBody.getChildren().setAll(body);
    }
    
    @FXML
    public void handleCadastrosBicicletas() throws IOException {
        AnchorPane body = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLBicicletas.fxml"));
        anchorPaneBody.getChildren().setAll(body);
    }
    
    @FXML
    public void handleProcessosAluguel() throws IOException{
        AnchorPane body = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAlugueis.fxml"));
        anchorPaneBody.getChildren().setAll(body);
    }
    
     @FXML
    public void handleMenuItemProcessosDevolucao() throws IOException{
//        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneProcessosVendas.fxml"));
//        anchorPane.getChildren().setAll(a);
//              MONTAR TELA
    }
    
    
    @FXML
    public void handleMenuItemGraficosAluguelPorMes() throws IOException{
//        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneGraficosVendasPorMes.fxml"));
//        anchorPane.getChildren().setAll(a);
    } 

    @FXML
    public void handleMenuItemGraficosAluguelPorAno() throws IOException{
//        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneGraficosVendasPorMes.fxml"));
//        anchorPane.getChildren().setAll(a);
//              MONTAR TELA

    }
    
    @FXML
    public void handleMenuItemGraficosAluguelPorCliente() throws IOException{
//        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneGraficosVendasPorMes.fxml"));
//        anchorPane.getChildren().setAll(a);
//              MONTAR TELA

    }
    
    @FXML
    public void handleMenuItemRelatoriosQuantidadeBicicletas() throws IOException{
//        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneRelatoriosQuantidadeProdutos.fxml"));
//        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatoriosQuantidadeBicicletasAlugadas() throws IOException{
//        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneRelatoriosQuantidadeProdutos.fxml"));
//        anchorPane.getChildren().setAll(a);
//              MONTAR TELA

    }
    
    @FXML
    public void handleMenuItemRelatoriosQuantidadeBicicletasDisponiveis() throws IOException{
//        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneRelatoriosQuantidadeProdutos.fxml"));
//        anchorPane.getChildren().setAll(a);
//              MONTAR TELA

    }    
    
}
