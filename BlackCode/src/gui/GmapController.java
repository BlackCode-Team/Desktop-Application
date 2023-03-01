package gui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


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
