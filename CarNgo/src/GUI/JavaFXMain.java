/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entity.Utilisateur;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Create and configure the user interface components
        // Add event handlers and layout information as needed
        // Add components to a scene
        // Set the scene on the primary stage
        Parent root = FXMLLoader.load(getClass().getResource("GUI/createUser.fxml"));
//        TableView<Utilisateur> table = new TableView<>();
//        VBox root = new VBox(table);
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

//Parent root = FXMLLoader.load(getClass().getResource("/gui/GestionVehicule.fxml"));
//
//        Scene scene = new Scene(root);
//        stage.setTitle("carngo");
//        stage.setScene(scene);
//        stage.show();
