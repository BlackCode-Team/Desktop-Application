package gui;

import entities.Badge;
import entities.Offre;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.Ioffre;
import services.badgeService;
import services.offreService;
import utils.MyConnection;


public class AffichageOffreController implements Initializable {

    @FXML
    private TableView<Offre> table_offre;
    @FXML
    private TableColumn<Offre,String> nom_column;
    @FXML
    private TableColumn<Offre,String> desciption_column;
    @FXML
    private TableColumn<Badge,String> typebadge_column;
    @FXML
    private Button bntmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnannuler;
    @FXML
    private TextField nomtxt;
    @FXML
    private TextArea descriptiontxt;
    @FXML
    private ImageView img;
    @FXML
    private ComboBox<String> combobadge;
    //private ObservableList<Offre> offreData = FXCollections.observableArrayList();

    Connection myconnex ;
    
    public AffichageOffreController(){
        myconnex = MyConnection.getInstance().getMyconnex();
    }
    Ioffre offreService = new offreService();
    Ioffre offre = new offreService();
    Badge badge = new Badge();
    badgeService badgeService = new badgeService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/logo.png"));
        img.setImage(image1);
       nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
       desciption_column.setCellValueFactory(new PropertyValueFactory<>("description"));   
       typebadge_column.setCellValueFactory(new PropertyValueFactory<>("typebadge"));
    List<String> a=badgeService.getTypesBadge();
       combobadge.getItems().addAll(a);
       
        
        load(); 
    }  

    @FXML
    private void modifier(ActionEvent event) {
        String nom = nomtxt.getText();
        String description = descriptiontxt.getText();
        String typebadge = combobadge.getValue();
        int idbadge = badgeService.getIdByTypeBadge(typebadge);
        Offre o1 = new Offre(nom, description, idbadge);
     
        Offre offreSelectionne = table_offre.getSelectionModel().getSelectedItem();
        if (offreSelectionne != null) {
            int idoffre = offreSelectionne.getIdoffre(); // récupérer l'ID du badge sélectionné
            offre.modifierOffre(idoffre, o1);            
            
            nomtxt.clear();
            descriptiontxt.clear();
            combobadge.getItems().clear();
         }
        
        load();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        String nom = nomtxt.getText();
        String description = descriptiontxt.getText();
        String typebadge = combobadge.getValue();
          int idbadge = badgeService.getIdByTypeBadge(typebadge);
        Offre o1 = new Offre(nom, description, idbadge);
    Offre offreSelectionne = table_offre.getSelectionModel().getSelectedItem();
    if (offreSelectionne != null) {
         int idoffre = offreSelectionne.getIdoffre(); // récupérer l'ID du badge sélectionné
            offre.supprimerOffre(idoffre);            
            load();
            nomtxt.clear();
            descriptiontxt.clear();
            combobadge.getItems().clear();
         }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String nom = nomtxt.getText();
        String description = descriptiontxt.getText();
        String typebadge = combobadge.getValue();
        int idbadge = badgeService.getIdByTypeBadge(typebadge);
        Offre offre = new Offre(nom, description, idbadge);
        offreService.ajouterOffre(offre);
        load();
        nomtxt.clear();
        descriptiontxt.clear();
        combobadge.getItems().clear();
   }

    @FXML
    private void annuler(ActionEvent event) {
            nomtxt.clear();
            descriptiontxt.clear();
            combobadge.getItems().clear();
    }
    public void load(){
        ObservableList<Offre> offres = FXCollections.observableArrayList();
        List<Offre> offrelist= new offreService().afficherOffre();
        for(Offre B:offrelist){
            offres.add(B);
        }
        table_offre.setItems(offres);
    }
    
    
    @FXML
    public void tableClick(MouseEvent event){
        Offre offre = table_offre.getSelectionModel().getSelectedItem();
        nomtxt.setText(offre.getNom());
        descriptiontxt.setText(offre.getDescription());
        combobadge.setValue(offre.getTypebadge());

    }
    
    public void LoadtypeBadge (ActionEvent actionEvent){
        
     try{
         Statement stmt = myconnex.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT typebadge FROM badge");
         ObservableList data = FXCollections.observableArrayList();
         while (rs.next()){
             data.add(new String(rs.getString(1)));
         }
         combobadge.setItems(data);
     }catch (Exception e){
        e.printStackTrace();
     }
    
}
}
