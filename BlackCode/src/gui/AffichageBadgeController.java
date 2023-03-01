package gui;

import entities.Badge;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.Ibadge;
import services.badgeService;


public class AffichageBadgeController implements Initializable {
    
    @FXML
    private TextField nomtxt;
    @FXML
    private TextField nbrepoint;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnannuler;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnactualiser;
    @FXML
    private ImageView img;
    
    @FXML
    private TableView<Badge> table_badge;
    @FXML
    private TableColumn<Badge,String> type_badge_column;
    @FXML
    private TableColumn<Badge,Integer> nbre_point_column;
    Ibadge badgeService = new badgeService();
    Ibadge badge = new badgeService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/logo.png"));
        img.setImage(image1);
      
        type_badge_column.setCellValueFactory(new PropertyValueFactory<>("typebadge"));
        nbre_point_column.setCellValueFactory(new PropertyValueFactory<>("nbrepoint"));   
        load(); 
        //setButtonStates(false, true, true);
    }    
    @FXML
    public void ajouter (ActionEvent event){
        
    String nom = nomtxt.getText();
    if (nom.matches("[0-9]+")){
        int Nbrepoint = Integer.parseInt(nbrepoint.getText());
        Badge B1 = new Badge(nom,Nbrepoint);
        badge.ajouterBadge(B1);
        nomtxt.clear();
        nbrepoint.clear();
    load();
    }else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Valeur invalide");
        alert.setContentText("Le champ 'typebadge' doit être composé uniquement de chiffres.");
        alert.showAndWait();
    }
   
    }
    
    @FXML
    public void modifier(ActionEvent event) {
    String nom = nomtxt.getText();
    int Nbrepoint = Integer.parseInt(nbrepoint.getText());
    Badge B1 = new Badge(nom,Nbrepoint);
    Badge badgeSelectionne = table_badge.getSelectionModel().getSelectedItem();
    if (badgeSelectionne != null) {
        int idbadge = badgeSelectionne.getIdbadge(); // récupérer l'ID du badge sélectionné
        badge.modifierBadge(idbadge, B1);
        nomtxt.clear();
        nbrepoint.clear();
        btnajouter.setDisable(false);
         }
    }
    
    @FXML
    public void supprimer(ActionEvent event){
    String nom = nomtxt.getText();
    int Nbrepoint = Integer.parseInt(nbrepoint.getText());
    Badge B1 = new Badge(nom,Nbrepoint);
    Badge badgeSelectionne = table_badge.getSelectionModel().getSelectedItem();
    if (badgeSelectionne != null) {
        int idbadge = badgeSelectionne.getIdbadge(); // récupérer l'ID du badge sélectionné
        badge.supprimerBadge(idbadge);
        load();
        nomtxt.clear();
        nbrepoint.clear();
        btnajouter.setDisable(false);
         }
    }
    
     @FXML
    public void annuler(ActionEvent event) {
        nomtxt.setText("");
        nbrepoint.setText("");
        btnajouter.setDisable(false);
//         setButtonStates(false, true, true);
        
    }
    public void load(){
        ObservableList<Badge> badges = FXCollections.observableArrayList();
        List<Badge> badgelist= new badgeService().afficherBadge();
        for(Badge B:badgelist){
            badges.add(B);
        }
        table_badge.setItems(badges);
    }
    
    public void tableClick(MouseEvent event){
        Badge badge = table_badge.getSelectionModel().getSelectedItem();
        nomtxt.setText(badge.getTypebadge());
       nbrepoint.setText(Integer.toString(badge.getNbrepoint()));
               btnajouter.setDisable(true);


//        setButtonStates(true, false, false);
    }
    
    
    
//    public void actualiser (ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.
//        load(getClass().getResource("affichageBadge.fxml"));
//Scene scene = new Scene(root);
//    }
//    
    
    
}
