package onmove.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import onmove.model.dao.AluguelDAO;
import onmove.model.dao.BicicletaDAO;
import onmove.model.dao.ClienteDAO;
import onmove.model.dao.DevolucaoDAO;
import onmove.model.dao.ItemDeAluguelDAO;
import onmove.model.database.Database;
import onmove.model.database.DatabaseFactory;
import onmove.model.domain.Aluguel;
import onmove.model.domain.Bicicleta;
import onmove.model.domain.Cliente;
import onmove.model.domain.Devolucao;
import onmove.model.domain.ItemDeAluguel;
import onmove.model.domain.ItemDeDevolucao;


public class FXMLCadastrarDevolucoesController implements Initializable {
    @FXML
    private ComboBox<Cliente> comboBoxCliente;
    @FXML
    private DatePicker datePickerDataDevolucao;
    @FXML
    private TableView<ItemDeAluguel> tableViewItensDeAluguel;
    @FXML
    private TableColumn<ItemDeAluguel, Bicicleta> tableColumnItemDeAluguelBicicleta;
    @FXML
    private TableColumn<ItemDeAluguel, Integer> tableColumnItemDeAluguelQuantidade;
    @FXML
    private TableColumn<ItemDeAluguel, Double> tableColumnItemDeAluguelValor;
    
    @FXML
    private ComboBox comboBoxBicicleta;
    @FXML
    private TextField textFieldItemDeDevolucaoQuantidade;
    @FXML
    private TableView<ItemDeDevolucao> tableViewItensDeDevolucao;
    @FXML
    private TableColumn<ItemDeDevolucao, Bicicleta> tableColumnItemDeDevolucaoBicicleta;
    @FXML
    private TableColumn<ItemDeDevolucao, Integer> tableColumnItemDeDevolucaoQuantidade;
    @FXML
    private TableColumn<ItemDeDevolucao, Double> tableColumnItemDeDevolucaoValor;
    @FXML
    private CheckBox checkBoxPago;
    @FXML
    private TextField textFieldValor;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonAdicionar;
    
    private List<Cliente> listClientes;
    private List<Bicicleta> listBicicletas;
    private ObservableList<Cliente> observableListClientes;
    private ObservableList<Bicicleta> observableListBicicletas;
    private ObservableList<ItemDeDevolucao> observableListItensDeDevolucao;
    private ObservableList<ItemDeAluguel> observableListItensDeAluguel;

    
    private List<ItemDeAluguel> listItensDeAluguel;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final BicicletaDAO bicicletaDAO = new BicicletaDAO();
    private final AluguelDAO aluguelDAO = new AluguelDAO();
    private final DevolucaoDAO devolucaoDAO = new DevolucaoDAO();
    private final ItemDeAluguelDAO itemDeAluguelDAO = new ItemDeAluguelDAO();


    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Devolucao devolucao;
    private Aluguel aluguel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        bicicletaDAO.setConnection(connection);
        aluguelDAO.setConnection(connection);
        itemDeAluguelDAO.setConnection(connection);
        
        
        carregarComboBoxClientes();
        carregarComboBoxBicicletas();
        
        
        tableColumnItemDeDevolucaoBicicleta.setCellValueFactory(new PropertyValueFactory<>("bicicleta"));
        tableColumnItemDeDevolucaoQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tableColumnItemDeDevolucaoValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
        comboBoxCliente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> carregarItensAlugados(newValue));
        System.out.println(comboBoxCliente.getSelectionModel().selectedItemProperty());
       
    }   
    
    public void carregarItensAlugados(Cliente cliente) {
        System.out.println(cliente.getCdCliente());
        System.out.println(aluguelDAO.buscarPorCliente(cliente));
        aluguelDAO.buscarPorCliente(cliente);
        System.out.println(aluguel.getItensDeAluguel());
        
        observableListItensDeAluguel = FXCollections.observableArrayList(aluguel.getItensDeAluguel());
        tableColumnItemDeAluguelBicicleta.setCellValueFactory(new PropertyValueFactory<>("bicicleta"));
        tableColumnItemDeAluguelQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tableColumnItemDeAluguelValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    public void carregarComboBoxClientes() {
        listClientes = clienteDAO.listar();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        comboBoxCliente.setItems(observableListClientes);
    }

    public void carregarComboBoxBicicletas() {
        listBicicletas = bicicletaDAO.listar();
        observableListBicicletas = FXCollections.observableArrayList(listBicicletas);
        comboBoxBicicleta.setItems(observableListBicicletas);
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Devolucao getDevolucao() {
        return this.devolucao;
    }

    public void setDevolucao(Devolucao devolucao) {
        this.devolucao = devolucao;
    }
    
    public Aluguel getAluguel() {
        return this.aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    @FXML
    public void handleButtonAdicionar() {
        Bicicleta bicicleta;
        ItemDeDevolucao itemDeDevolucao = new ItemDeDevolucao();
        if (comboBoxBicicleta.getSelectionModel().getSelectedItem() != null) {
            bicicleta = (Bicicleta) comboBoxBicicleta.getSelectionModel().getSelectedItem();
            if (bicicleta.getQuantidade() >= Integer.parseInt(textFieldItemDeDevolucaoQuantidade.getText())) {
                itemDeDevolucao.setBicicleta((Bicicleta) comboBoxBicicleta.getSelectionModel().getSelectedItem());
                itemDeDevolucao.setQuantidade(Integer.parseInt(textFieldItemDeDevolucaoQuantidade.getText()));
                itemDeDevolucao.setValor(itemDeDevolucao.getBicicleta().getPreco() * itemDeDevolucao.getQuantidade());
              
                devolucao.getItensDeDevolucao().add(itemDeDevolucao); 
                devolucao.setValor(devolucao.getValor() + itemDeDevolucao.getValor()); 
                
                observableListItensDeDevolucao = FXCollections.observableArrayList(devolucao.getItensDeDevolucao());
                tableViewItensDeDevolucao.setItems(observableListItensDeDevolucao);
                textFieldValor.setText(String.format("%.2f", devolucao.getValor()));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Problemas na escolha do bicicleta!");
                alert.setContentText("Não existe a quantidade de bicicletas disponíveis no estoque!");
                alert.show();
            }
        }
    }

    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            devolucao.setCliente((Cliente) comboBoxCliente.getSelectionModel().getSelectedItem());
            devolucao.setPago(checkBoxPago.isSelected());
            devolucao.setDataDevolucao(datePickerDataDevolucao.getValue());
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }

    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        if (comboBoxCliente.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cliente inválido!\n";
        }
        if (datePickerDataDevolucao.getValue() == null) {
//            Verificar se a data está atrasada com a do aluguel e então gerar a multa
            errorMessage += "Data inválida!\n";
        }
        if (observableListItensDeDevolucao == null) {
            errorMessage += "Itens de Aluguel inválidos!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
}
