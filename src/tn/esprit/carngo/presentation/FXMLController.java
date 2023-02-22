/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.carngo.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mhcab
 */
public class FXMLController implements Initializable {
      @FXML
    private AnchorPane home;

    @FXML
    private ToggleButton itbutt;

    @FXML
    void loaditin(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("map.fxml"));

        // Load the FXML file
        Parent anotherRoot = loader.load();

        // Get a reference to the current scene and replace its root node with the root node of the new FXML file
        Scene currentScene = home.getScene();
        currentScene.setRoot(anotherRoot);
    }
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
