package gui;


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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import services.ItineraireService;
import utils.MyConnection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private TextField depart;

    @FXML
    private TextField arriver;

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
//************************************************* variable d'index pour le tableau
           int index=-1;
           
           

    ObservableList<itineraire> itineraire ;  //ObservableList un list de type fxcollection bech najem naffichi les itineraire aw bel a7ra liste itineraire il heya type fil asel collection cheya7
     MyConnection cnx = null;
     Statement st = null;
     ItineraireService  rcd = new ItineraireService ();
     
     
     //**********************************controle de saisi
        private boolean adressevalide(){
      Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(arriver.getText());
        if(m.find() && m.group().equals(arriver.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type validé !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un type DESTINATION validé !");
                alert.showAndWait();
           
            return false;            
        }
     }
                private boolean depvalide(){
      Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(depart.getText());
        if(m.find() && m.group().equals(depart.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type validé !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un type DEPART validé !");
                alert.showAndWait();
           
            return false;            
        }
                }
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
                            
                            
                            
                            
                            
     
    @FXML
    void ajout_itineraire(ActionEvent event) {
        ItineraireService it = new ItineraireService();
        if(adressevalide()&&depvalide()&&estvalide()&&idvalide()){
        
        String var1=arriver.getText();
        String var2=depart.getText();
        String var3=tempsest.getText();
        int var4=Integer.parseInt(var3);
        String var5=iditin.getText();
        int var6=Integer.parseInt(var5);
        

       itineraire i =new itineraire();
        
      i.setPointDepart(var2);
      i.setPointDestination(var1);
      i.setDureeEstime(var4);
      i.setIdUser(var6);
      it.ajouterItineraire(i);
       populateItineraires();
      arriver.clear();
      depart.clear();
      tempsest.clear();
      iditin.clear();;

       
   } 

    }

    @FXML
    void mod_itineraire(ActionEvent event) {
       ItineraireService it = new ItineraireService();
        if(adressevalide()&&depvalide()&&estvalide()&&idvalide()){
        
        String var1=arriver.getText();
        String var2=depart.getText();
        String var3=tempsest.getText();
        int var4=Integer.parseInt(var3);
        String var5=iditin.getText();
        int var6=Integer.parseInt(var5);
        

      itineraire i =new itineraire(var2,var1,var4);
      it.modifierItineraire(var6, i);
       populateItineraires();
      arriver.clear();
      depart.clear();
      tempsest.clear();
      iditin.clear();;

       
   }
    }

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
        Statement stmt = MyConnection.getInstance().getMyconnex().createStatement();

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
    depart.setText(departcol.getCellData(index));
    arriver.setText(arivcol.getCellData(index));
    tempsest.setText(estimcol.getCellData(index).toString());
    idlabel.setText("le nom du User : "+(it.NameUser(iduser.getCellData(index))));
}
      @FXML
    void gmapwin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gmap.fxml"));

        // Load the FXML file
        Parent anotherRoot = loader.load();

        // cree une nouvelle scene ou nsetiw stage fiha ma3neha lpage fxml
        Scene anotherScene = new Scene(anotherRoot);
        Stage anotherStage = new Stage();
        anotherStage.setScene(anotherScene);

        // pour afficher le stage
        anotherStage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       populateItineraires();
       tableit.setOnMouseClicked(this::getit);
     
    }
    
       
    
}
