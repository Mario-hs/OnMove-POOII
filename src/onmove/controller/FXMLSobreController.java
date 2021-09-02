
package onmove.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLSobreController implements Initializable {
    
    @FXML
    private Label labelTeam;
    
    private Socket socket;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleConnection();
    }    
    
    public void handleConnection(){
        try{
            socket = new Socket("34.125.46.96", 12345);

            Thread thread = new Thread(new ThreadSobre(socket, labelTeam));
            thread.start();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLSobreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
