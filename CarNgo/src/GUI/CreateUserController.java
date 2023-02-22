/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.MyConnection;
import Entity.Utilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jokser
 */
public class CreateUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @FXML
//    GridPane button_grid;
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

    Connection cnx;
    int myIndex;
    Integer id;
    PreparedStatement pst;
    ObservableList<Utilisateur> users = FXCollections.observableArrayList();

    public CreateUserController() {
        cnx = MyConnection.getInstance().getCnx();
    }

    // ---------------------------------------
    public void table() {
        cnx = MyConnection.getInstance().getCnx();

        try {
            pst = cnx.prepareStatement("SELECT  `nom`, `prenom`, `email`, `cin`FROM `utilisateur` ");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Utilisateur user1 = new Utilisateur();

                    user1.setNom(rs.getString("nom"));
                    user1.setPrenom(rs.getString("prenom"));
                    user1.setEmail(rs.getString("email"));
                    user1.setCin(rs.getString("cin"));
                    users.add(user1);
                }
            }
            CrudTable.setItems(users);
            NomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            PrenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            CinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            

        } catch (SQLException ex) {
            Logger.getLogger(CreateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        CrudTable.setRowFactory(tv -> {
            TableRow<Utilisateur> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = CrudTable.getSelectionModel().getSelectedIndex();
                    
                    Integer id = Integer.parseInt(String.valueOf(CrudTable.getItems().get(myIndex).getIdUser()));
                    TxtNom.setText(CrudTable.getItems().get(myIndex).getNom());
                    TxtPrenom.setText(CrudTable.getItems().get(myIndex).getPrenom());
                    TxtCin.setText(CrudTable.getItems().get(myIndex).getCin());
                    TxtEmail.setText(CrudTable.getItems().get(myIndex).getEmail());

                }
            });
            return myRow;
        });

    }

    // ---------------------------------------
    @FXML
    void Add(ActionEvent event) {
        String nom, prenom, cin, email;
        nom = TxtNom.getText();
        prenom = TxtPrenom.getText();
        cin = TxtCin.getText();
        email = TxtEmail.getText();

        try {
            String req1 = "INSERT INTO `utilisateur`(`nom`, `prenom`, `email`, `cin`) "
                    + "VALUES ('" + nom + "','" + prenom + "','" + email + "','" + cin + "')";

            Statement ste = cnx.createStatement();
            id = ste.executeUpdate(req1);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Agent Registation");

            alert.setHeaderText("Agent Registation!!");
            alert.setContentText("Agent ajouté !! !");

            alert.showAndWait();
            table();

            TxtNom.setText("");
            TxtPrenom.setText("");
            TxtCin.setText("");
            TxtEmail.setText("");
            TxtNom.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(CreateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // ---------------------------------------
    @FXML
    void Delete(ActionEvent event
    ) {

    }
// ---------------------------------------

    @FXML
    void Update(ActionEvent event
    ) {

    }

    // ---------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources
    ) {
        cnx = MyConnection.getInstance().getCnx();
        table();
    }

}

//   
