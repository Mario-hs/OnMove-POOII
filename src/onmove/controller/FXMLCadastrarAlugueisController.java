package onmove.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
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
import onmove.model.dao.BicicletaDAO;
import onmove.model.dao.ClienteDAO;
import onmove.model.database.Database;
import onmove.model.database.DatabaseFactory;
import onmove.model.domain.Aluguel;
import onmove.model.domain.Bicicleta;
import onmove.model.domain.Cliente;
import onmove.model.domain.ItemDeAluguel;

public class FXMLCadastrarAlugueisController implements Initializable {
    @FXML
    private ComboBox comboBoxAluguelCliente;
    @FXML
    private DatePicker datePickerAluguelDataEmprestimo;
    @FXML
    private DatePicker datePickerAluguelDataDevolucao;
    @FXML
    private CheckBox checkBoxAluguelPago;
    @FXML
    private ComboBox comboBoxAluguelBicicleta;
    @FXML
    private TableView<ItemDeAluguel> tableViewItensDeAluguel;
    @FXML
    private TableColumn<ItemDeAluguel, Bicicleta> tableColumnItemDeAluguelBicicleta;
    @FXML
    private TableColumn<ItemDeAluguel, Integer> tableColumnItemDeAluguelQuantidade;
    @FXML
    private TableColumn<ItemDeAluguel, Double> tableColumnItemDeAluguelValor;
    @FXML
    private TextField textFieldAluguelValor;
    @FXML
    private TextField textFieldAluguelItemDeAluguelQuantidade;
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
    private ObservableList<ItemDeAluguel> observableListItensDeAluguel;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final BicicletaDAO bicicletaDAO = new BicicletaDAO();

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Aluguel aluguel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        bicicletaDAO.setConnection(connection);
        carregarComboBoxClientes();
        carregarComboBoxBicicletas();
        tableColumnItemDeAluguelBicicleta.setCellValueFactory(new PropertyValueFactory<>("bicicleta"));
        tableColumnItemDeAluguelQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tableColumnItemDeAluguelValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }    
    
    public void carregarComboBoxClientes() {
        listClientes = clienteDAO.listar();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        comboBoxAluguelCliente.setItems(observableListClientes);
    }

    public void carregarComboBoxBicicletas() {
        listBicicletas = bicicletaDAO.listar();
        observableListBicicletas = FXCollections.observableArrayList(listBicicletas);
        comboBoxAluguelBicicleta.setItems(observableListBicicletas);
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
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
        ItemDeAluguel itemDeAluguel = new ItemDeAluguel();
        if (comboBoxAluguelBicicleta.getSelectionModel().getSelectedItem() != null) {
            bicicleta = (Bicicleta) comboBoxAluguelBicicleta.getSelectionModel().getSelectedItem();
            if (bicicleta.getQuantidade() >= Integer.parseInt(textFieldAluguelItemDeAluguelQuantidade.getText())) {
                itemDeAluguel.setBicicleta((Bicicleta) comboBoxAluguelBicicleta.getSelectionModel().getSelectedItem());
                itemDeAluguel.setQuantidade(Integer.parseInt(textFieldAluguelItemDeAluguelQuantidade.getText()));
                itemDeAluguel.setValor(itemDeAluguel.getBicicleta().getPreco() * itemDeAluguel.getQuantidade());
              
                aluguel.getItensDeAluguel().add(itemDeAluguel); 
                aluguel.setValor(aluguel.getValor() + itemDeAluguel.getValor()); 
                
                observableListItensDeAluguel = FXCollections.observableArrayList(aluguel.getItensDeAluguel());
                tableViewItensDeAluguel.setItems(observableListItensDeAluguel);
                textFieldAluguelValor.setText(String.format("%.2f", aluguel.getValor()));
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
            aluguel.setCliente((Cliente) comboBoxAluguelCliente.getSelectionModel().getSelectedItem());
            aluguel.setPago(checkBoxAluguelPago.isSelected());
            aluguel.setDataEmprestimo(datePickerAluguelDataEmprestimo.getValue());
            aluguel.setDataDevolucao(datePickerAluguelDataDevolucao.getValue());
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
        LocalDate dataemprestimo = datePickerAluguelDataEmprestimo.getValue();
        LocalDate datadevolucao =  datePickerAluguelDataDevolucao.getValue();
        String errorMessage = "";
        if (comboBoxAluguelCliente.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cliente inválido!\n";
        }
        if (datadevolucao.isBefore(dataemprestimo)){
            errorMessage += "Data inválida!\nData de devolução deve ser depois da data de emprestimo!";
        }
        if (datePickerAluguelDataEmprestimo.getValue() == null) {
            errorMessage += "Data inválida!\n";
        }
        if (datePickerAluguelDataDevolucao.getValue() == null) {
            errorMessage += "Data inválida!\n";
        }
        if (observableListItensDeAluguel == null) {
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
