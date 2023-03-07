package gui;

import entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ReclamationService;
import utils.Mail;
import utils.MyConnection;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReclamationAgentController implements Initializable {
    @FXML
    private TextArea txtcontenu;
    @FXML
    private Button btnrepondre;
    @FXML
    private HBox btndelete;
    @FXML
    private Label nbreclamation;
    @FXML
    private TableColumn<Reclamation, Date> datecolumn;
    @FXML
    private TableColumn<Reclamation, String> matriculecolumn;
    @FXML
    private TableColumn<Reclamation, String> reclamationcolumn;
    @FXML
    private TableColumn<Reclamation, Integer> num;
    @FXML
    private TableColumn<Reclamation, String> cincolumn;
    @FXML
    private TableView<Reclamation> tab;

    List<Reclamation> listReclamations = new ArrayList<>();
    ObservableList<Reclamation> observableListReclamations;
    @FXML
    void tableevent(MouseEvent event) { }
    ReclamationService rs=new ReclamationService();

    Connection myconnex= MyConnection.getInstance().getMyconnex();

    public void consulter() {
        List<Reclamation> listReclamations = rs.afficherentite();
        ObservableList<Reclamation> observableListReclamations = FXCollections.observableArrayList(listReclamations);
        num = new TableColumn<>("ID");
        num.setCellValueFactory(new PropertyValueFactory<>("idreclamation"));
        matriculecolumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        reclamationcolumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        cincolumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        tab.setItems(observableListReclamations);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nbreclamation.setText(String.valueOf(rs.calculReclamation())+ "Réclamations");
        observableListReclamations = FXCollections.observableArrayList(); // initialize ls here
//        btnenvoyer.setOnAction(event -> {
//            try {
//                envoyer(event);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

        consulter();
    }

    @FXML
    void repondre(MouseEvent event) {
        Reclamation r=new Reclamation();
        r=tab.getSelectionModel().getSelectedItem();
        Mail.envoyer_reponsereclamation(r);
        consulter();
    }

    public void delete(MouseEvent event) {
            Reclamation r = tab.getSelectionModel().getSelectedItem();
            int id = r.getId();
            rs.supprimerentite(id);
            tab.getItems().removeAll(r);

    }
}
