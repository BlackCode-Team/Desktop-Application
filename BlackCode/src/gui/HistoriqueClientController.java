package gui;

import entities.Historique;
import entities.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.HistoriqueService;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HistoriqueClientController implements Initializable {

    @FXML
    private Button btndelete;

    @FXML
    private TableColumn<Historique, Date> datecolumn;

    @FXML
    private TableColumn<Historique, String> destinationcolumn;

    @FXML
    private TableColumn<Historique, String> matriculecolumn;

    @FXML
    private TableColumn<Historique, Integer> numrescolumn;

    @FXML
    private TableColumn<Historique, Integer> prixcolumn;

    @FXML
    private TableView<Historique> tab;


    HistoriqueService hs=new HistoriqueService();

    @FXML
    void delete()  {
        hs.supprimerentite(tab.getSelectionModel().getSelectedItem());
        System.out.println(tab.getSelectionModel().getSelectedItem().getIdhistorique());
    }
    @FXML
    void deletehist(ActionEvent event) {
        delete();
        tab.getItems().removeAll(tab.getSelectionModel().getSelectedItem());
        System.out.println(tab);
        tab.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Historique> list = FXCollections.observableArrayList(hs.afficherHistorique());
        matriculecolumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        destinationcolumn.setCellValueFactory(new PropertyValueFactory<>("pointarrivee"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        numrescolumn.setCellValueFactory(new PropertyValueFactory<>("idreservation"));
        prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tab.setItems(list);

    }
}

