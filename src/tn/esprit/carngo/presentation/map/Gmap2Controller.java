package tn.esprit.carngo.presentation.map;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Gmap2Controller implements Initializable {

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine webEngine = webView.getEngine();

        // Load the HTML file containing the map
        webEngine.load(getClass().getResource("mapBG.html").toExternalForm());
        webEngine.javaScriptEnabledProperty();
    }
}
