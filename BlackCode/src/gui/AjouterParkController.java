package gui;

import entities.PlaceStationnement;
import entities.Vehicule;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import services.PlaceStationnementServices;
import services.VehiculeServices;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterParkController implements Initializable {
    @FXML
    private Button btnadd;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnmod;

    @FXML
    private TableView<PlaceStationnement> tab;
    @FXML
    private TableColumn<PlaceStationnement, String> placecolumn;

    @FXML
    private TextField txtplace;

    @FXML
    private TableColumn<Vehicule, String> voiturescolumn;

    PlaceStationnementServices ps=new PlaceStationnementServices();
    @FXML
    void add(ActionEvent actionEvent) {
        PlaceStationnement p = new PlaceStationnement();
        p.setLocation(txtplace.getText());
        ps.addEntity(p);
        Alert al2=new Alert(Alert.AlertType.CONFIRMATION);
        al2.setTitle("succes");
        al2.setHeaderText("nouvelle place de stationnement  !");
        al2.showAndWait();
    }
    private void consulter() {
        VehiculeServices vs = new VehiculeServices();
        ObservableList<PlaceStationnement> locations = FXCollections.observableArrayList(ps.findAllEntity());
        for (PlaceStationnement location : locations) {
            List<Vehicule> listeVehicules = vs.findbylocation(location.getLocation());
            List<String> matricules = new ArrayList<>();
            for (Vehicule vehicule : listeVehicules) {
                matricules.add(vehicule.getMatricule());
            }
            location.setMatricules(matricules);
        }
        tab.setItems(locations);
    }

    @FXML
    void delete()  {
        ps.deleteEntity(tab.getSelectionModel().getSelectedItem());
        System.out.println(tab.getSelectionModel().getSelectedItem().getId());
    }
    @FXML
    private void deletev(ActionEvent event) {
        delete();
        tab.getItems().removeAll(tab.getSelectionModel().getSelectedItem());
        System.out.println(tab);
        tab.refresh();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Définir la colonne "Location" et lier sa propriété à la propriété "location" de l'objet PlaceStationnement
        placecolumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        // Définir la colonne "Matricule" et lier sa propriété à la propriété "matricule" de l'objet Vehicule
        voiturescolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vehicule, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehicule, String> param) {
                return new SimpleStringProperty(param.getValue().getMatricule());
            }
        });
        consulter();
    }


    public void update(ActionEvent actionEvent) {
    }

    public void search(MouseEvent event) {
    }
}
