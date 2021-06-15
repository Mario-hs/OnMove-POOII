
package onmove;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("view/FXMLVBoxMain.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Sistema de Vendas (ON MOVE)");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
