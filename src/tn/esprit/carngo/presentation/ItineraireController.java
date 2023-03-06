/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.carngo.presentation;



import com.lynden.gmapsfx.GoogleMapView;
import java.io.IOException;
import tn.esprit.carngo.entities.itineraire;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import tn.esprit.carngo.service.ItineraireService;
import tn.esprit.carngo.utils.MyConnection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mhcab
 */

public class ItineraireController implements Initializable {
  @FXML
    private AnchorPane itinpage;
    public ObservableList<itineraire> data=FXCollections.observableArrayList();
 



    @FXML
    private Button ajoutit;

    @FXML
    private TextField tempsest;
    @FXML
    private TextField iditin;

        @FXML
    private ComboBox<String> depcomb;

    @FXML
    private ComboBox<String> arivcomb;
        @FXML
    private WebView webView;
        
        //variable pour recuperer les maps 
 String map="map/map.html";
    
    //les choix du combobox 
    ObservableList<String> choices = FXCollections.observableArrayList( "Bizerte", "Ghazella", "Ariana");
//************************************************* variable d'index pour le tableau

           
           

    ObservableList<itineraire> itineraire ;  //ObservableList un list de type fxcollection bech najem naffichi les itineraire aw bel a7ra liste itineraire il heya type fil asel collection cheya7
     MyConnection cnx = null;
     Statement st = null;
     ItineraireService  rcd = new ItineraireService ();
     
     

                                private boolean estvalide(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tempsest.getText());
        if(m.find() && m.group().equals(tempsest.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type validé !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un type TEMPS ESTIME validé !");
                alert.showAndWait();
           
            return false;            
        }
                }
                            private boolean idvalide(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(iditin.getText());
        if(m.find() && m.group().equals(iditin.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type validé !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un type ID USER validé !");
                alert.showAndWait();
           
            return false;            
        }
                }
     //*****************************************************************************
                            
                            
                            
                            
                            
     //*******************************ajouter itineraire ********************************
    @FXML
    void ajout_itineraire(ActionEvent event) {
        ItineraireService it = new ItineraireService();
        if(estvalide()&&idvalide()){

        String var3=tempsest.getText();
        int var4=Integer.parseInt(var3);
        String var5=iditin.getText();
        int var6=Integer.parseInt(var5);
       String dep= depcomb.getValue();
       String ariv= arivcomb.getValue();
        

       itineraire i =new itineraire();
        
      i.setPointDepart(dep);
      i.setPointDestination(ariv);
      i.setDureeEstime(var4);
      i.setIdUser(var6);
      it.ajouterItineraire(i);
        if(depcomb.getValue().equalsIgnoreCase("Bizerte")&&arivcomb.getValue().equalsIgnoreCase("Ariana")||depcomb.getValue().equalsIgnoreCase("Ariana")&&arivcomb.getValue().equalsIgnoreCase("Bizerte"))
    {
       map="map/mapBA.html";
//     tempsest.setText(Integer.toString(60));
//     tempsest.setDisable(true);
    }
    else if(depcomb.getValue().equalsIgnoreCase("Ariana")&&arivcomb.getValue().equalsIgnoreCase("Ghazella")||depcomb.getValue().equalsIgnoreCase("Ghazella")&&arivcomb.getValue().equalsIgnoreCase("Ariana")){
     map="map/recupMap.html";
//     tempsest.setText(Integer.toString(15));
//     tempsest.setDisable(true);
     
    }
    else if(depcomb.getValue().equalsIgnoreCase("Ghazella")&&arivcomb.getValue().equalsIgnoreCase("Bizerte")||depcomb.getValue().equalsIgnoreCase("Bizerte")&&arivcomb.getValue().equalsIgnoreCase("Ghazella")){
     map="map/mapBG.html";
//     tempsest.setText(Integer.toString(30));
//     tempsest.setDisable(true);
    }
       WebEngine webEngine = webView.getEngine();
       webEngine.load(getClass().getResource(map).toExternalForm());
       webEngine.javaScriptEnabledProperty();


      tempsest.clear();
      iditin.clear();;

       
   } 

    }
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       depcomb.setItems(choices);
       arivcomb.setItems(choices);

       
        
       WebEngine webEngine = webView.getEngine();
       webEngine.load(getClass().getResource(map).toExternalForm());
       webEngine.javaScriptEnabledProperty();

    
  
   

        // Load the HTML file containing the map
    
    
    
       
    }
}
