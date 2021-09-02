package onmove.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class ThreadSobre implements Runnable {
    private Socket socket;
    private Label labelTeam;
    private ArrayList<String> team;
    String dev;
    int i = 0;
    
     public ThreadSobre(Socket s, Label labelTeam) {
        this.socket = s;
        this.labelTeam = labelTeam;
    }
     
    @Override
    public void run(){
        DataOutputStream saida;
        try {
            saida = new DataOutputStream(socket.getOutputStream());
            saida.writeInt(4);
            
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            this.team = (ArrayList<String>) entrada.readObject();
//            System.out.print(team.get(0));
        } catch (IOException ex) {
            Logger.getLogger(ThreadSobre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadSobre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true){
            if(i >= team.size()){
                i = 0;
            }
            dev = (String) team.get(i);
            Platform.runLater(() -> labelTeam.setText(dev));
            System.out.println(">"+ i);
            sleep();
            i++;
        }
    }
    
    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLSobreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
