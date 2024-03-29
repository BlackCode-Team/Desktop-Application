/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



//import com.lynden.gmapsfx.GoogleMapView;
import java.io.IOException;
import entities.itineraire;
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
import javafx.scene.Node;
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
import services.ItineraireService;
import utils.MyConnection;
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
    void ajout_itineraire(ActionEvent event) throws IOException {
        ItineraireService it = new ItineraireService();
        if(estvalide()&&idvalide()){

       
  
        String var5=iditin.getText();
        int var6=Integer.parseInt(var5);
       String dep= depcomb.getValue();
       String ariv= arivcomb.getValue();
        

       itineraire i =new itineraire();
        
      i.setPointDepart(dep);
      i.setPointDestination(ariv);
      i.setIdUser(var6);
      
        if(depcomb.getValue().equalsIgnoreCase("Bizerte")&&arivcomb.getValue().equalsIgnoreCase("Ariana")||depcomb.getValue().equalsIgnoreCase("Ariana")&&arivcomb.getValue().equalsIgnoreCase("Bizerte"))
    {
       map="map/mapBA.html";
       i.setKilometrage(60);
       i.setDureeEstime(45);

//     tempsest.setText(Integer.toString(60));
//     tempsest.setDisable(true);
    }
    else if(depcomb.getValue().equalsIgnoreCase("Ariana")&&arivcomb.getValue().equalsIgnoreCase("Ghazella")||depcomb.getValue().equalsIgnoreCase("Ghazella")&&arivcomb.getValue().equalsIgnoreCase("Ariana")){
     map="map/recupMap.html";
     i.setKilometrage(10);

//     tempsest.setText(Integer.toString(15));
//     tempsest.setDisable(true);
     
    }
    else if(depcomb.getValue().equalsIgnoreCase("Ghazella")&&arivcomb.getValue().equalsIgnoreCase("Bizerte")||depcomb.getValue().equalsIgnoreCase("Bizerte")&&arivcomb.getValue().equalsIgnoreCase("Ghazella")){
     map="map/mapBG.html";
     i.setKilometrage(50);

//     tempsest.setText(Integer.toString(30));
//     tempsest.setDisable(true);
    }
       it.ajouterItineraire(i);
       WebEngine webEngine = webView.getEngine();
       webEngine.load(getClass().getResource(map).toExternalForm());
       webEngine.javaScriptEnabledProperty();


      tempsest.clear();
      iditin.clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoisirVehicule.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(newScene);
            int iditineraire=it.findItineraireByUser(idUser);
            System.out.println("idUser"+idUser);
            ClientItemController crudVehiculecontroller = loader.getController();
            crudVehiculecontroller.setIdItin(iditineraire);
            crudVehiculecontroller.setIduser(idUser);

   } 

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       depcomb.setItems(choices);
       arivcomb.setItems(choices);

       WebEngine webEngine = webView.getEngine();
       webEngine.load(getClass().getResource(map).toExternalForm());
       webEngine.javaScriptEnabledProperty();


        // Load the HTML file containing the map
       
    }
    int idUser;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
        iditin.setText(String.valueOf(idUser));

    }
}

