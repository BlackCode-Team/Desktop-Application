package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainCRUDUser extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
       
        Parent root = FXMLLoader.load(getClass().getResource("CreateUser.fxml"));

        primaryStage.setTitle("My JavaFX Application");
        
        // Load the FXML file
        

        // Create a scene with the FXML content
        Scene scene = new Scene(root);

        // Set the scene on the primary stage and show it
        primaryStage.setScene(scene);
       
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
