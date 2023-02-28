package gui;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import entities.EtatReservation;
import entities.Reservation;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import services.ReservationService;


public class AjouterReservationController extends Application implements Initializable {


    @FXML
    TextField clienttxt;

    @FXML
    DatePicker datePicker;
    @FXML
    TextField itinerairetxt;
    @FXML
    Button enregbtn;
    @FXML
    Button btnRetour;
    @FXML
    ComboBox<String> comboVehic;
    private Reservation reservation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReservationService reservationService = new ReservationService();
        List<String> a = reservationService.getMatricule();
        comboVehic.getItems().addAll(a);
    }

    @FXML
    public void saveReservation(ActionEvent event) {
        try {
            ReservationService reservationService = new ReservationService();

            if (isValid()) {  // Récupération des valeurs des champs
                int clientId = Integer.parseInt(clienttxt.getText());
                String matricule = comboVehic.getValue();
                int vehiculeId = reservationService.getVehiculeIdByMatricule(matricule);
                LocalDate localDate = datePicker.getValue();
                Date datefin = Date.valueOf(localDate);
                Calendar cal = Calendar.getInstance();
                Date date = new Date(cal.getTimeInMillis());
                int itineraireId = Integer.parseInt(itinerairetxt.getText());
                EtatReservation status = EtatReservation.en_cours;

                // Validation des champs de saisie de la réservation
                // Récupération des valeurs saisies dans les champs de saisie
                // Création de la réservation
Reservation reservation = new Reservation(date, datefin, vehiculeId, clientId, itineraireId, status);
                // Ajout de la réservation dans la base de données
                if (reservationService.ajouterReservation(reservation)) {
                    JOptionPane.showMessageDialog(null, "client ajouté!");

                }

            }      // Redirection vers la page de détails de la réservation
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void btnRetour(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reserver.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("AjouterReservation.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("AjouterReservation!");
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
    // ...

    public boolean isValid() {
        // Validation des champs de saisie de la réservation
        // Retourne true si tous les champs sont valides, false sinon
        if (clienttxt.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Champ Vide!");
            return false;
        }
//        if (vehiculetxt.getText().length() < 1) {
//            JOptionPane.showMessageDialog(null, "Champ Vide!");
//            return false;
//        }
        if (itinerairetxt.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Champ Vide!");
            return false;
        } else {
            return true;
        }
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation res) {
        reservation = res;
        clienttxt.setText(Integer.toString(res.getIduser()));
        itinerairetxt.setText(Integer.toString(res.getIditineraire()));
        datePicker.setValue(res.getDatedebut().toLocalDate());

    }
}
