
package interfaceFX;

import gestionbadges.GestionBadges;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class mainWindow extends Application {
 
@Override
public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("affichageOffre.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("ajouterBadge!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) {
        
    launch(args);
    
    }
    
    
}
