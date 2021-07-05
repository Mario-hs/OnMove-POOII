package onmove.controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import onmove.model.dao.ItemDeDevolucaoDAO;
import onmove.model.domain.Bicicleta;
import onmove.model.domain.Cliente;
import onmove.model.domain.Devolucao;
import onmove.model.domain.ItemDeDevolucao;

public class FXMLDetalhesDevolucaoController implements Initializable {
    @FXML
    private Label labelCodigo;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelDataDevolucao;
    @FXML
    private Label labelPago;
    @FXML
    private TableView<ItemDeDevolucao> tableViewItensDeDevolucao;
    @FXML
    private TableColumn<ItemDeDevolucao, Bicicleta> tableColumnItemDeDevolucaoBicicleta;
    @FXML
    private TableColumn<ItemDeDevolucao, Integer> tableColumnItemDeDevolucaoQuantidade;
    @FXML
    private TableColumn<ItemDeDevolucao, Double> tableColumnItemDeDevolucaoValor;
    @FXML
    private Button buttonFechar;
    
    private List<ItemDeDevolucao> listDevolucoes;
    private ObservableList<ItemDeDevolucao> observableListDevolucoes;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Devolucao devolucao;
    private final ItemDeDevolucaoDAO itemDeDevolucaoDAO = new ItemDeDevolucaoDAO();
    private Cliente cliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Devolucao getDevolucao() {
        return devolucao;
    }
    
    public void setDevolucao(Devolucao devolucao) {
        this.devolucao = devolucao;
        this.labelCodigo.setText(String.valueOf(devolucao.getCdDevolucao()));
        this.labelNome.setText(String.valueOf(devolucao.getCliente()));
        this.labelDataDevolucao.setText(String.valueOf(devolucao.getDataDevolucao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        this.labelPago.setText(String.valueOf(devolucao.getPago()));
//        carregarTabelView(devolucao);
    }
    
//    public void carregarTabelView(Devolucao devolucao) {
//        tableColumnItemDeDevolucaoBicicleta.setCellValueFactory(new PropertyValueFactory<>("bicicleta"));
//        tableColumnItemDeDevolucaoQuantidade.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
//        tableColumnItemDeDevolucaoValor.setCellValueFactory(new PropertyValueFactory<>("cliente"));
//
//        listDevolucoes = itemDeDevolucaoDAO.listarPorDevolucao(devolucao);
//        
//        observableListDevolucoes = FXCollections.observableArrayList(devolucao.getItensDeDevolucao());
//        tableViewItensDeDevolucao.setItems(observableListDevolucoes);
//    }
    
    @FXML
    public void handleButtonFechar() {
        dialogStage.close();
    }
}
