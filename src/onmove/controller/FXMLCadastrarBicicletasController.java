package onmove.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import onmove.model.domain.Bicicleta;
import onmove.model.domain.Modelo;
import onmove.model.domain.Marca;
import onmove.model.dao.ModeloDAO;
import onmove.model.dao.MarcaDAO;
import onmove.model.database.Database;
import onmove.model.database.DatabaseFactory;

public class FXMLCadastrarBicicletasController implements Initializable {
    @FXML
    private TextField textFieldNome;
    @FXML
    private ComboBox comboBoxMarca;
    @FXML 
    private ComboBox comboBoxModelo;
    @FXML
    private TextField textFieldPreco;
    @FXML
    private TextField textFieldQuantidade;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    
    private List<Marca> listMarcas;
    private List<Modelo> listModelos;
    private ObservableList<Marca> observableListMarcas;
    private ObservableList<Modelo> observableListModelos;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Bicicleta bicicleta;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final MarcaDAO marcaDAO = new MarcaDAO();
    private final ModeloDAO modeloDAO = new ModeloDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        marcaDAO.setConnection(connection);
        modeloDAO.setConnection(connection);
        carregarComboBoxMarcas();
        carregarComboBoxModelos();
    }    
    
    public void carregarComboBoxMarcas() {
        listMarcas = marcaDAO.listar();
        observableListMarcas = FXCollections.observableArrayList(listMarcas);
        comboBoxMarca.setItems(observableListMarcas);
    }

    public void carregarComboBoxModelos() {
        listModelos = modeloDAO.listar();
        observableListModelos = FXCollections.observableArrayList(listModelos);
        comboBoxModelo.setItems(observableListModelos);
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

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicicleta bicicleta) {
       this.bicicleta = bicicleta;
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {
            bicicleta.setNome(textFieldNome.getText());
            bicicleta.setPreco(Double.parseDouble(textFieldPreco.getText()));
            bicicleta.setQuantidade(Integer.parseInt(textFieldQuantidade.getText()));
            bicicleta.setMarca((Marca) comboBoxMarca.getSelectionModel().getSelectedItem());
            bicicleta.setModelo((Modelo) comboBoxModelo.getSelectionModel().getSelectedItem());
            buttonConfirmarClicked = true;
            dialogStage.close();
        }

    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        if (textFieldNome.getText() == null || textFieldNome.getText().length() <= 2) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldPreco.getText() == null || textFieldPreco.getText().length() <= 0) {
            errorMessage += "Preço inválido!\n";
        }
        if (textFieldQuantidade.getText() == null || textFieldQuantidade.getText().length() < 1) {
            errorMessage += "Quantidade inválido!\n";
        }
        if (comboBoxMarca.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Marca inválida!\n";
        }
        if (comboBoxModelo.getValue() == null) {
            errorMessage += "Modelo inválido!\n";
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
