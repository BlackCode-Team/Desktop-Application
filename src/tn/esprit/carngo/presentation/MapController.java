/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.carngo.presentation;


import java.awt.event.MouseEvent;
import tn.esprit.carngo.entities.itineraire;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import tn.esprit.carngo.service.ItineraireService;
import tn.esprit.carngo.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author mhcab
 */
public class MapController implements Initializable {

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
     
     
     //********on utilise cette fonction pour lors des ajouts et modification ou spprission les table fait un mise a jour 
     public void updateTable_r(){
     itineraire= (ObservableList<itineraire>) rcd.afficherItineraire();
     tableit.getItems().setAll(itineraire);
    }
     //*********************************************************************************************************************
     
     
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
                alert.setContentText("Veuillez entrer un type validé !");
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
                alert.setContentText("Veuillez entrer un type validé !");
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
                alert.setContentText("Veuillez entrer un type validé !");
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
                alert.setContentText("Veuillez entrer un type validé !");
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
       updateTable_r();
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
       updateTable_r();
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
              updateTable_r();
    }

    //fonction pour recupere les donnes selectionne depuis le tableau
        @FXML
//    void getit(MouseEvent event) {
//        index=tableit.getSelectionModel().getSelectedIndex();
//         if(index<=-1){
//         return ;
//     }
//     iditin.setText(iditincol.getCellData(index).toString());
//     depart.setText(departcol.getCellData(index));
//     arriver.setText(arivcol.getCellData(index));
//     tempsest.setText(estimcol.getCellData(index).toString());
//     idlabel.setText(iduser.getCellData(index).toString()+": id user ki a passer l'itineraire");
//    }
    
    //fonction pour afficher les donnees dans un tableau 
    public void affiche(){
         try {
            String sql = "select iduser,iditineraire,pointdepart,pointarrivee,dureeestimee from itineraire";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet s = st.executeQuery(sql);
            while (s.next()) {

                itineraire i = new itineraire(s.getInt(1),s.getInt(2),s.getString(3),s.getString(4),s.getInt(5),s.getInt(1));
                data.add(i);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
                departcol.setCellValueFactory(new PropertyValueFactory<>("point_depart")); 
                 arivcol.setCellValueFactory(new PropertyValueFactory<>("point_destination"));
                 distancecol.setCellValueFactory(new PropertyValueFactory<>("distance"));
                 estimcol.setCellValueFactory(new PropertyValueFactory<>("temps_estime"));
                 iditincol.setCellValueFactory(new PropertyValueFactory<>("id_itineraire"));
                 iduser.setCellValueFactory(new PropertyValueFactory<>("id_user_responsable"));
                 
                 tableit.setItems(data);
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       affiche();
      
    }
    
       
    
}
