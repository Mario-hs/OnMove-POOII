package onmove.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import onmove.model.domain.Bicicleta;

public class FXMLDetalhesBicicletaController implements Initializable {
    @FXML
    private Label labelBicicletaCodigo;
    @FXML
    private Label labelBicicletaNome;
    @FXML
    private Label labelBicicletaMarca;
    @FXML
    private Label labelBicicletaModelo;
    @FXML
    private Label labelBicicletaPreco;
    @FXML
    private Label labelBicicletaQuantidade;
    @FXML
    private Button buttonFechar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Bicicleta bicicleta;
    
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

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
        this.labelBicicletaCodigo.setText(String.valueOf(bicicleta.getCdBicicleta()));
        this.labelBicicletaNome.setText(String.valueOf(bicicleta.getNome()));
        this.labelBicicletaMarca.setText(String.valueOf(bicicleta.getMarca()));
        this.labelBicicletaModelo.setText(String.valueOf(bicicleta.getModelo()));
        this.labelBicicletaPreco.setText(String.valueOf(bicicleta.getPreco()));
        this.labelBicicletaQuantidade.setText(String.valueOf(bicicleta.getQuantidade()));
    }
    
    @FXML
    public void handleButtonFechar() {
        dialogStage.close();
    }
    
}
