package onmove.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import onmove.model.domain.Aluguel;

public class FXMLDetalhesAluguelController implements Initializable {
    @FXML
    private Label labelAluguelCodigo;
    @FXML
    private Label labelAluguelCliente;
    @FXML
    private Label labelAluguelDataAluguel;
    @FXML
    private Label labelAluguelDataDevolucao;
    @FXML
    private Label labelAluguelValor;
    @FXML
    private Label labelAluguelPago;
    @FXML
    private Button buttonFechar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Aluguel aluguel;
    
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

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
        this.labelAluguelCodigo.setText(String.valueOf(aluguel.getCdAluguel()));
        this.labelAluguelCliente.setText(String.valueOf(aluguel.getCliente()));
        this.labelAluguelDataAluguel.setText(String.valueOf(aluguel.getDataEmprestimo()));
        this.labelAluguelDataDevolucao.setText(String.valueOf(aluguel.getDataDevolucao()));
        this.labelAluguelValor.setText(String.valueOf(aluguel.getValor()));
        this.labelAluguelPago.setText(String.valueOf(aluguel.getPago()));
    }
    
    @FXML
    public void handleButtonFechar() {
        dialogStage.close();
    }
    
    
}
