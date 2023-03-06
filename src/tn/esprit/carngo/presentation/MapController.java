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

public class MapController implements Initializable {
  @FXML
    private AnchorPane itinpage;
   @FXML
    private TableView<itineraire> tableit;
    public ObservableList<itineraire> data=FXCollections.observableArrayList();
    @FXML
    private TableColumn<itineraire, String> departcol;

    @FXML
    private TableColumn<itineraire, String> arivcol;

    @FXML
    private TableColumn<itineraire, Integer> distancecol;
        @FXML
    private TableColumn<itineraire, Integer> estimcol;
      @FXML
    private TableColumn<itineraire, Integer> iditincol;

    @FXML
    private TableColumn<itineraire, Integer> iduser;
    
    
    @FXML
    private Label idlabel;




    @FXML
    private Button ajoutit;

    @FXML
    private Button suppit;

    @FXML
    private Button modit;

    @FXML
    private TextField tempsest;
    @FXML
    private TextField iditin;
    @FXML
    private TextField inrech;
        @FXML
    private ComboBox<String> depcomb;

    @FXML
    private ComboBox<String> arivcomb;
    
    //les choix du combobox 
    ObservableList<String> choices = FXCollections.observableArrayList( "Bizerte", "Ghazella", "Ariana");
//************************************************* variable d'index pour le tableau
           int index=-1;
           
           

    ObservableList<itineraire> itineraire ;  //ObservableList un list de type fxcollection bech najem naffichi les itineraire aw bel a7ra liste itineraire il heya type fil asel collection cheya7
     MyConnection cnx = null;
     Statement st = null;
     ItineraireService  rcd = new ItineraireService ();
     
     
     //**********************************controle de saisi
//        private boolean adressevalide(){
//      Pattern p = Pattern.compile("[a-zA-Z ]+");
//        Matcher m = p.matcher(arriver.getText());
//        if(m.find() && m.group().equals(arriver.getText())){
//            return true;
//        }else{
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Type validé !");
//                alert.setHeaderText(null);
//                alert.setContentText("Veuillez entrer un type DESTINATION validé !");
//                alert.showAndWait();
//           
//            return false;            
//        }
//     }
//                private boolean depvalide(){
//      Pattern p = Pattern.compile("[a-zA-Z ]+");
//        Matcher m = p.matcher(depart.getText());
//        if(m.find() && m.group().equals(depart.getText())){
//            return true;
//        }else{
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Type validé !");
//                alert.setHeaderText(null);
//                alert.setContentText("Veuillez entrer un type DEPART validé !");
//                alert.showAndWait();
//           
//            return false;            
//        }
//                }
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
       populateItineraires();

      tempsest.clear();
      iditin.clear();;

       
   } 

    }
    
//***************************modifier itineraire ****************************
    @FXML
    void mod_itineraire(ActionEvent event) {
       ItineraireService it = new ItineraireService();
        if(estvalide()&&idvalide()){
        

        String var3=tempsest.getText();
        int var4=Integer.parseInt(var3);
        String var5=iditin.getText();
        int var6=Integer.parseInt(var5);
       String dep= depcomb.getValue();
       String ariv= arivcomb.getValue();
        
        

      itineraire i =new itineraire(ariv,dep,var4);
      it.modifierItineraire(var6, i);
       populateItineraires();

      tempsest.clear();
      iditin.clear();;

       
   }
    }

    
    //*******************************supprimer itineraire *********************************
    @FXML
    void supp_itineraire(ActionEvent event) {
           ItineraireService  it = new  ItineraireService ();
           itineraire i= new itineraire();   
              i= tableit.getSelectionModel().getSelectedItem();
              it.supprimerItineraire(i);
              populateItineraires();
    }

    
    //fonction pour afficher les donnees dans un tableau 
    public void populateItineraires() {
    try {
        Statement stmt = MyConnection.getInstance().getCnx().createStatement();

        ResultSet rs = stmt.executeQuery("SELECT iduser,iditineraire,pointdepart,pointarrivee,kilometrage,dureeestimee FROM itineraire");
        
        ObservableList<itineraire> itinerairesList = FXCollections.observableArrayList();

        while (rs.next()) {
            int idUser = rs.getInt(1);
            int idItineraire = rs.getInt(2);
            String pointDepart = rs.getString(3);
            String pointDestination = rs.getString(4);
            int kilometrage = rs.getInt(5);
            int DureeEstime = rs.getInt(6);

            itineraire i = new itineraire(idUser, idItineraire, pointDepart, pointDestination,kilometrage, DureeEstime);
            itinerairesList.add(i);
        }
        
        iduser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        iditincol.setCellValueFactory(new PropertyValueFactory<>("idItineraire"));
        departcol.setCellValueFactory(new PropertyValueFactory<>("pointDepart"));
        arivcol.setCellValueFactory(new PropertyValueFactory<>("pointDestination"));
        distancecol.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
        estimcol.setCellValueFactory(new PropertyValueFactory<>("DureeEstime"));

        tableit.setItems(itinerairesList);
     
    } catch (SQLException e) {
        e.printStackTrace();
    }
   
}
    
    //fonction 3maltha juste ba3ed bech traja3li nom mta3 luser brl jointur ba3ed bel karhba twali modele 
    

        //fonction pour recupere les donnes selectionne depuis le tableau
        @FXML
 private void getit(MouseEvent event) {
    index = tableit.getSelectionModel().getSelectedIndex();
    if (index <= -1) {
        return;
    }
    ItineraireService it=new ItineraireService();
    iditin.setText(iditincol.getCellData(index).toString());
    iditin.setDisable(true);
    depcomb.setValue(departcol.getCellData(index));
    arivcomb.setValue(arivcol.getCellData(index));

    tempsest.setText(estimcol.getCellData(index).toString());
    idlabel.setText("le nom du User : "+(it.NameUser(iduser.getCellData(index))));
}
 //***************************************FONCTION RECHERCHE DYNAMIQUE *********************************************
     public void recherche() {
    try {
        Statement stmt = MyConnection.getInstance().getCnx().createStatement();

        ResultSet rs = stmt.executeQuery("SELECT iduser,iditineraire,pointdepart,pointarrivee,kilometrage,dureeestimee FROM itineraire");
        
        ObservableList<itineraire> itinerairesList = FXCollections.observableArrayList();

        while (rs.next()) {
            int idUser = rs.getInt(1);
            int idItineraire = rs.getInt(2);
            String pointDepart = rs.getString(3);
            String pointDestination = rs.getString(4);
            int kilometrage = rs.getInt(5);
            int DureeEstime = rs.getInt(6);

            itineraire i = new itineraire(idUser, idItineraire, pointDepart, pointDestination,kilometrage, DureeEstime);
            itinerairesList.add(i);
        }
              String nom=inrech.getCharacters().toString();
               ObservableList<itineraire> listrech =  itinerairesList.stream()
                .filter(e -> e.getPointDepart().toLowerCase().startsWith(nom.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
 
        
        iduser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        iditincol.setCellValueFactory(new PropertyValueFactory<>("idItineraire"));
        departcol.setCellValueFactory(new PropertyValueFactory<>("pointDepart"));
        arivcol.setCellValueFactory(new PropertyValueFactory<>("pointDestination"));
        distancecol.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
        estimcol.setCellValueFactory(new PropertyValueFactory<>("DureeEstime"));

        tableit.setItems(listrech);
     
    } catch (SQLException e) {
        e.printStackTrace();
    }
     }
     //************************************ RECHERCHE DYNAMIQUE ********************
    @FXML
    void rech(KeyEvent event){
        recherche();
    }
    
 @FXML
public void openmap(ActionEvent event) {
    String url="";
  
    try {
          if(depcomb.getValue().equalsIgnoreCase("Bizerte")&&arivcomb.getValue().equalsIgnoreCase("Ariana")||depcomb.getValue().equalsIgnoreCase("Ariana")&&arivcomb.getValue().equalsIgnoreCase("Bizerte"))
    {
       url="map/gmap3.fxml";
    }
    else if(depcomb.getValue().equalsIgnoreCase("Ariana")&&arivcomb.getValue().equalsIgnoreCase("Ghazella")||depcomb.getValue().equalsIgnoreCase("Ghazella")&&arivcomb.getValue().equalsIgnoreCase("Ariana")){
     url="map/gmap.fxml";
    }
    else if(depcomb.getValue().equalsIgnoreCase("Ghazella")&&arivcomb.getValue().equalsIgnoreCase("Bizerte")||depcomb.getValue().equalsIgnoreCase("Bizerte")&&arivcomb.getValue().equalsIgnoreCase("Ghazella")){
     url="map/gmap2.fxml";
    }
        // Load the FXML file of the new window
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        Parent root = fxmlLoader.load();

        // Create a new stage for the new window
        Stage stage = new Stage();
        stage.setTitle("Map");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       populateItineraires();
       depcomb.setItems(choices);
       arivcomb.setItems(choices);
       tableit.setOnMouseClicked(this::getit);
     
    }
    
       
    
}
