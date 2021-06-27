package onmove;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("view/FXMLHome.fxml"));
        
        Scene scene = new Scene(root, 800, 550);
        
        stage.setScene(scene);
        stage.setTitle("Sistema de Vendas (ON MOVE)");
        stage.setResizable(false);
        stage.show();
        setStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage(){
        return stage;
    }
    
    public static void setStage(Stage stage){
        Main.stage = stage;
    }
}
