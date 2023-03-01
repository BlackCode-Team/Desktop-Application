package gui;

import entities.Reclamation;
import entities.TypeVehicule;
import entities.Vehicule;
import entities.itineraire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ItineraireService;
import services.ReclamationService;
import utils.MyConnection;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReclamationController implements Initializable {
    @FXML
    private TextArea txtcontenu;
    @FXML
    private Button btnannuler;

    @FXML
    private Button btndelete;
    @FXML
    private Button btnenvoyer;
    @FXML
    private ChoiceBox<String> chboxvehicule;
    @FXML
    private TableColumn<Reclamation, Date> datecolumn;
    @FXML
    private TableColumn<Reclamation, String> matriculecolumn;
    @FXML
    private TableColumn<Reclamation, String> reclamationcolumn;
    @FXML
    private TableColumn<Reclamation, Integer> idreclamation;
    @FXML
    private TableColumn<Reclamation, String> statuscolumn;
    @FXML
    private TableView<Reclamation> tab;


    List<Reclamation> listReclamations = new ArrayList<>();
    ObservableList<Reclamation> observableListReclamations;
    @FXML
    void tableevent(MouseEvent event) { }
    ReclamationService rs=new ReclamationService();

    Connection myconnex= MyConnection.getInstance().getMyconnex();

    @FXML
    void envoyer(ActionEvent event) throws IOException {
        String contenu=txtcontenu.getText();
        String matricule=chboxvehicule.getValue();

        if(chboxvehicule.getValue() == null || txtcontenu.getText().isEmpty()) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("champs vide");
            alert2.setHeaderText(" Veuillez choisir le type de véhicule");
            alert2.showAndWait();
        }else {
            Reclamation r=new Reclamation(contenu,local_date(),matricule);
            rs.ajouterentite(r);
//            Alert al2=new Alert(Alert.AlertType.CONFIRMATION);
//            al2.setTitle("succes");
//            al2.setHeaderText(" Réclamation envoyée et nous vous répondrons dans les plus brefs délai!");
// al2.showAndWait();
            JOptionPane.showMessageDialog(null, " Réclamation envoyée et nous vous répondrons dans les plus brefs délai!");
            observableListReclamations.clear();
            observableListReclamations.addAll(rs.afficherentite());
            tab.setItems(observableListReclamations);
            txtcontenu.clear();
            chboxvehicule.getSelectionModel().clearSelection();
        }
    }

    public static Date local_date (){
        LocalDate localDate = LocalDate.now();
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /*public static LocalDate local_date (){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }*/

    public void consulter() {
        List<Reclamation> listReclamations = rs.afficherentite();
        ObservableList<Reclamation> observableListReclamations = FXCollections.observableArrayList(listReclamations);
        idreclamation = new TableColumn<>("ID");
        idreclamation.setCellValueFactory(new PropertyValueFactory<>("idreclamation"));
        matriculecolumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        reclamationcolumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        statuscolumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tab.setItems(observableListReclamations);
    }


    public void delete() {
        Reclamation r=new Reclamation();
        r=tab.getSelectionModel().getSelectedItem();
        int idreclamation = r.getId();
        rs.supprimerentite(idreclamation);
    }

    @FXML
    private void deleterec(ActionEvent event) {
        Reclamation r = tab.getSelectionModel().getSelectedItem();
        int id = r.getId();
        rs.supprimerentite(id);
        tab.getItems().removeAll(r);
    }




    public List<String> recupererMatricule(int iduser) {
        try {
            String req1 = "SELECT v.matricule FROM reservation r " +
                    "INNER JOIN vehicule v ON r.idvehicule = v.idvehicule WHERE r.iduser = ?";
            PreparedStatement ps = myconnex.prepareStatement(req1);
            ps.setInt(1, iduser);
            ResultSet rs = ps.executeQuery();
            List<String> matricules = new ArrayList<>();
            while (rs.next()) {
                String matricule = rs.getString("matricule");
                matricules.add(matricule);
            }
            return matricules;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> matricules = recupererMatricule(16);
        ObservableList<String> options = FXCollections.observableArrayList(matricules);
        //chboxvehicule.getItems().setAll(options);
        chboxvehicule.getItems().setAll("7896TUN231","9763TUN231");
        observableListReclamations = FXCollections.observableArrayList(); // initialize ls here
        btnenvoyer.setOnAction(event -> {
            try {
                envoyer(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        consulter();
    }


    public void refresh(ActionEvent actionEvent) {
    }
}