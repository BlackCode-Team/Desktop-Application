package gui;


import utils.MyConnection;
import entities.Utilisateur;
import services.UtilisateurService;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class CreateUserController implements Initializable {

    
     @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonUpdate;

    @FXML
    private TableColumn<Utilisateur, String> CinColumn;

    @FXML
    private TableView<Utilisateur> CrudTable;

    @FXML
    private TableColumn<Utilisateur, String> EmailColumn;

    @FXML
    private TableColumn<Utilisateur, String> NomColumn;

    @FXML
    private TableColumn<Utilisateur, String> PrenomColumn;

    @FXML
    private TextField TxtCin;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtNom;

    @FXML
    private TextField TxtPrenom;
    
   
    UtilisateurService us = new UtilisateurService();
    List<Utilisateur> listUsers= new ArrayList<Utilisateur>();
    ObservableList<Utilisateur> ObsUsers;
    Connection cnx;
 // -------------------------------
    public Connection myConnection() {
        return cnx = MyConnection.getInstance().getMyconnex();
    }
 // -------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObsUsers= FXCollections.observableArrayList();
        NomColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("Nom"));
        PrenomColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("Prenom"));
        CinColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("Cin"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("Email"));
        System.out.println("Test observable list"+ObsUsers);
        CrudTable.setItems(ObsUsers);
        CrudTable.isEditable();
    }    
 // -------------------------------
    @FXML
    void Add(ActionEvent event) {
        myConnection();
        if (TxtNom.getText().isEmpty() || TxtPrenom.getText().isEmpty()|| TxtCin.getText().isEmpty()|| TxtEmail.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Vérifer les champs !");
        }
        else
        {
        String Nom = TxtNom.getText();
        String Prenom  = TxtPrenom.getText();
        String Cin = TxtCin.getText();
        String Email = TxtEmail.getText();
        Utilisateur u= new Utilisateur(TxtNom.getText(),TxtPrenom.getText(),TxtEmail.getText(),TxtCin.getText());
        
        us.ajouterUtilisateur(u);
            JOptionPane.showMessageDialog(null, "Utilisateur Ajouté !");
        TxtCin.clear();
        TxtEmail.clear();
        TxtNom.clear();
        TxtPrenom.clear();
        
        ObsUsers.clear();
        ObsUsers.addAll(us.afficherUtilisateur());
        CrudTable.setItems(ObsUsers);
        
        //JOptionPane.showMessageDialog(null, "Affichage successif!");
               
        }
        
        
    }
 // -------------------------------
    @FXML
    void Delete(ActionEvent event) {
        myConnection();
        Utilisateur selectedItem =CrudTable.getSelectionModel().getSelectedItem();
        if (selectedItem!=null) {
            CrudTable.getItems().remove(selectedItem);
            us.supprimerUtilisateur(selectedItem);
            JOptionPane.showMessageDialog(null, "Utilisateur Supprimé !");
        }
    }
  // -------------------------------  
    @FXML
    void Update(ActionEvent event) {
    myConnection();
    Utilisateur selectedItem = CrudTable.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
        JOptionPane.showMessageDialog(null, "Sélectionnez l'utilisateur à modifier !");
        return;
    }
    if (TxtNom.getText().isEmpty() || TxtPrenom.getText().isEmpty() || TxtCin.getText().isEmpty() || TxtEmail.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
        return;
    }
    Utilisateur updatedUser = new Utilisateur(TxtNom.getText(), TxtPrenom.getText(), TxtEmail.getText(), TxtCin.getText());
    us.modifierUtilisateur(selectedItem.getCin(), updatedUser);
    updateUserInList(updatedUser);
    JOptionPane.showMessageDialog(null, "Utilisateur modifié !");
    TxtNom.clear();
    TxtPrenom.clear();
    TxtCin.clear();
    TxtEmail.clear();
    CrudTable.refresh();
}
 // -------------------------------

    
   private void updateUserInList(Utilisateur updatedUser) {
    int index = ObsUsers.indexOf(updatedUser);
    if (index != -1) {
        ObsUsers.set(index, updatedUser);
                     }

    }
   
    
}