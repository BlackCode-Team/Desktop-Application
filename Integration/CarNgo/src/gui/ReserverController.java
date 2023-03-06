package gui;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import entities.Reservation;
import services.ReservationService;


public class ReserverController extends Application implements Initializable {

    @FXML
    TableView<Reservation> TableRes;
    @FXML
    TableColumn<Reservation, Integer> IDRes;
    @FXML
    TableColumn<Reservation, Integer> IDVehicule;
    @FXML
    TableColumn<Reservation, Integer> IDItineraire;
    @FXML
    TableColumn<Reservation, Integer> IDItineraireFin;
    @FXML
    TableColumn<Reservation, Integer> User;
    @FXML
    TableColumn<Reservation, Date> DateRes;
    @FXML
    TableColumn<Reservation, Date> DateFin;

    @FXML
    Button btnSupp;
    @FXML
    Button btnAjouterRes;
    @FXML
    Button btnMod;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshTable();
    }

//    @FXML
//    private void close(MouseEvent event) {
//    }
//
//    @FXML
//    private void getAddView(MouseEvent event) {
//    }
//
    private void refreshTable() {

        ReservationService reservationService = new ReservationService();
        IDRes.setCellValueFactory(new PropertyValueFactory<>("idreservation"));
        IDVehicule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        IDItineraire.setCellValueFactory(new PropertyValueFactory<>("pointdepart"));
        IDItineraireFin.setCellValueFactory(new PropertyValueFactory<>("pointarrivee"));
        User.setCellValueFactory(new PropertyValueFactory<>("cin"));
        DateRes.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        DateFin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        TableRes.setItems(reservationService.afficherReservations23());
   //     System.out.println(reservationService.afficherReservations23());
    }

    public void btnAjouterRes(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReservation.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.setTitle("Ajouter une réservation");
//            AjouterReservationController ajouterReservationController = loader.getController();
//            ajouterReservationController.setReservation(reservation);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void btnMod(ActionEvent event) throws IOException {
        // Vérifier si une réservation est sélectionnée
        int selectedIndex = TableRes.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Récupérer la réservation sélectionnée
            Reservation reservation = TableRes.getItems().get(selectedIndex);

            // Ouvrir une nouvelle fenêtre pour modifier la réservation
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReservation.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.setTitle("Modifier une réservation");

            ModifierReservationController modifierReservationController = loader.getController();
            modifierReservationController.setReservation(reservation);
            //    Mettre à jour la réservation dans la table si elle a été modifiée
            if (modifierReservationController.isValid()) {
                Reservation modifiedReservation = modifierReservationController.getReservation();
                ReservationService reservationService = new ReservationService();
                reservationService.modifierReservation(modifiedReservation.getIdreservation(), modifiedReservation);
                TableRes.getItems().set(selectedIndex, modifiedReservation);
            }
        } else {
            // Afficher un message d'erreur si aucune réservation n'est sélectionnée
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucune réservation sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une réservation à modifier.");
            alert.showAndWait();
        }
    }

    public void btnSupp(ActionEvent event) {
        Reservation reservation = TableRes.getSelectionModel().getSelectedItem();
        if (reservation != null) {
            int idReservation = reservation.getIdreservation();
            ReservationService reservationService = new ReservationService();
            reservationService.supprimerReservation(idReservation, reservation);
            TableRes.getItems().remove(reservation);
        } else {
            // Afficher un message d'erreur si aucune réservation n'est sélectionnée
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucune réservation sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une réservation à supprimer.");
            alert.showAndWait();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("Reserver.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestion des Reservation!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
