package gui;

import entities.TypeVehicule;
import entities.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.VehiculeServices;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CRUDVehiculeController implements Initializable {
    @FXML
    private TableColumn<?, ?> batteriecolumn;

    @FXML
    private Button btnadd;

    @FXML
    private Button delete;
    @FXML
    private Button btnmod;

    @FXML
    private ImageView btnsearch;

    @FXML
    private TableColumn<Vehicule, String> matriculecolumn;

    @FXML
    private TableColumn<Vehicule, String> modelecolumn;

    @FXML
    private TableColumn<Vehicule, String> prixcolumn;

    @FXML
    private TableColumn<Vehicule, String> puissancecolumn;

    @FXML
    private TableColumn<Vehicule, String> statuscolumn;

    @FXML
    private TableView<Vehicule> tab;

    @FXML
    private TableColumn<Vehicule, TypeVehicule> typecolumn;

    @FXML
    void add(ActionEvent event) throws IOException {
        // Charger FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterVehicule.fxml"));
        Parent root = loader.load();
        //  nouvelle scène avec le contenu de la scène Ajouter.fxml
        Scene scene = new Scene(root);
        // Obtenir la référence de la scène actuelle à partir du bouton "Ajouter"
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        // Afficher nouvelle scène
        stage.setScene(scene);
        stage.show();
    }

    VehiculeServices vs=new VehiculeServices();


    void consultertab(){
        ObservableList<Vehicule> list = FXCollections.observableArrayList(vs.findAllEntity());
        matriculecolumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        modelecolumn.setCellValueFactory(new PropertyValueFactory<>("modele"));
        typecolumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        puissancecolumn.setCellValueFactory(new PropertyValueFactory<>("puissance"));
        batteriecolumn.setCellValueFactory(new PropertyValueFactory<>("batterie"));
        prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        statuscolumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tab.setItems(list);
    }

    @FXML
    void update(ActionEvent actionEvent) {
        Vehicule selectedVehicule = tab.getSelectionModel().getSelectedItem();
        if (selectedVehicule != null) {
            selectedVehicule.setMatricule(matriculecolumn.getText());
            selectedVehicule.setModele(modelecolumn.getText());
            try {
                selectedVehicule.setPuissance(Integer.parseInt(puissancecolumn.getText()));
                selectedVehicule.setBatterie(Integer.parseInt(prixcolumn.getText()));
                vs.updateEntity(selectedVehicule);
                tab.refresh(); // actualise la vue de la table
            } catch (NumberFormatException e) {
                // traitement de l'erreur de conversion
                System.err.println("La valeur entrée pour la puissance ou la batterie n'est pas un nombre valide.");
            }
        }
    }

    @FXML
    void delete()  {
        vs.deleteEntity(tab.getSelectionModel().getSelectedItem());
        System.out.println(tab.getSelectionModel().getSelectedItem().getId());
    }
    @FXML
    private void deletev(ActionEvent event) {
        delete();
        tab.getItems().removeAll(tab.getSelectionModel().getSelectedItem());
        System.out.println(tab);
        tab.refresh();
    }

    @FXML
    void search(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Vehicule> list = FXCollections.observableArrayList(vs.findAllEntity());
        matriculecolumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        modelecolumn.setCellValueFactory(new PropertyValueFactory<>("modele"));
        typecolumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        puissancecolumn.setCellValueFactory(new PropertyValueFactory<>("puissance"));
        batteriecolumn.setCellValueFactory(new PropertyValueFactory<>("batterie"));
        prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        statuscolumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tab.setItems(list);

    }
}
