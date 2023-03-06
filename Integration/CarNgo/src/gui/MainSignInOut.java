package gui;


import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainSignInOut extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));

        stage.setTitle("My JavaFX Application");
        
        // Load the FXML file
        

        // Create a scene with the FXML content
        Scene scene = new Scene(root);

        // Set the scene on the primary stage and show it
        stage.setScene(scene);
       
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
