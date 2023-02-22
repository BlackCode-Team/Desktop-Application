/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.carngo.presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author mhcab
 */
public class GmapController implements Initializable {
    @FXML
    private WebView webview;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           WebEngine engine = webview.getEngine();

        String htmlFilePath = "file:C:\\Users\\mhcab\\OneDrive\\Bureau\\CarNgo\\src\\tn\\esprit\\carngo\\data\\map\\recupMap.html";
        engine.load(htmlFilePath);
    }    
    
}
