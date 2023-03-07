package gui;

import entities.Historique;
import java.io.IOException;
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
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HistoriqueAgentController extends Application implements Initializable {

    @FXML
    private Button btndelete;

    @FXML
    private TableColumn<Historique, Integer> cincolumn;

    @FXML
    private TableColumn<Historique, Date> datecolumn;

    @FXML
    private TableColumn<Historique, String> destinationcolumn;

    @FXML
    private TableColumn<Historique, String> matriculecolumn;

    @FXML
    private TableColumn<Historique , Integer> numrescolumn;

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
        ObservableList<Historique> list = FXCollections.observableArrayList(hs.afficherentiteAgent());
        matriculecolumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        cincolumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        destinationcolumn.setCellValueFactory(new PropertyValueFactory<>("pointarrivee"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        numrescolumn.setCellValueFactory(new PropertyValueFactory<>("idreservation"));
        tab.setItems(list);

    }
       @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("HistoriqueAgent.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestion des Reservation!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
