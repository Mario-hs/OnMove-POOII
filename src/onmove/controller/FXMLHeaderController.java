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
    private AnchorPane anchorPaneBody;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void handleScreen(String opcao) throws IOException {
         switch (opcao){
            case "clientes": 
                handleCadastrosClientes();
               break;
            case "bicicletas":
                handleCadastrosBicicletas();
               break;
            case "alugueis": 
                handleProcessosAluguel();
               break;
            case "devolucoes":
                handleProcessosDevolucao();
               break;
            case "multas": 
                handleProcessosMulta();
               break;
            case "graficoPorMes": 
                handleGraficosAluguelPorMes();
               break;
            case "graficoPorCliente":
               handleGraficosAluguelPorCliente();
               break;
            case "bicicletasAlugadas": 
                 handleRelatoriosQuantidadeBicicletasAlugadas();
                break;
            case "bicicletasDisponiveis":
                handleRelatoriosQuantidadeBicicletasDisponiveis();
               break;
            case "bicicletasCadastradas": 
                handleRelatoriosQuantidadeBicicletasCadastradas();
               break;
            case "equipe":
                handleEquipe();
                break;
        }
    }
    
    @FXML
    public void handleHome() throws IOException{
        System.out.println("voltar");
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
    public void handleProcessosDevolucao() throws IOException{
        AnchorPane body = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLDevolucoes.fxml"));
        anchorPaneBody.getChildren().setAll(body);
    }
    
    @FXML
    public void handleProcessosMulta() throws IOException{
//        AnchorPane body = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLMulta.fxml"));
//        anchorPaneBody.getChildren().setAll(body);
//              MONTAR TELA
    }
    
    @FXML
    public void handleGraficosAluguelPorMes() throws IOException{
        AnchorPane body = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLGraficoAluguelPorMes.fxml"));
        anchorPaneBody.getChildren().setAll(body);
    } 

    @FXML
    public void handleGraficosAluguelPorCliente() throws IOException{
//        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLAnchorPaneGraficosVendasPorMes.fxml"));
//        anchorPane.getChildren().setAll(a);
//              MONTAR TELA

    }
    
    @FXML
    public void handleRelatoriosQuantidadeBicicletasAlugadas() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLRelatoriosQuantidadeBicicletasAlugadas.fxml"));
        anchorPaneBody.getChildren().setAll(a);
    }
    
    @FXML
    public void handleRelatoriosQuantidadeBicicletasDisponiveis() throws IOException{
        AnchorPane body = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLRelatoriosQuantidadeBicicletasDisponiveis.fxml"));
        anchorPaneBody.getChildren().setAll(body);
    }   
    
    @FXML
    public void handleRelatoriosQuantidadeBicicletasCadastradas() throws IOException{
        AnchorPane body = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLRelatoriosQuantidadeBicicletasCadastradas.fxml"));
        anchorPaneBody.getChildren().setAll(body);
    } 
    
    @FXML
    public void handleEquipe() throws IOException{
        AnchorPane body = (AnchorPane) FXMLLoader.load(getClass().getResource("/onmove/view/FXMLSobre.fxml"));
        anchorPaneBody.getChildren().setAll(body);
    }
}
