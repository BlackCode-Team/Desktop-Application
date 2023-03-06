package gui;

import entities.EtatReservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import entities.Reservation;
import java.util.Calendar;
import services.ReservationService;

public class ModifierReservationController implements Initializable {

    @FXML
    TextField clienttxt;
    @FXML
    TextField vehiculetxt;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField itinerairetxt;
    @FXML
    Button enregbtn;
    @FXML
    Button btnRetour;
    private Reservation reservation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     *
     * @param event
     */
    @FXML
    public void saveModReservation(ActionEvent event) {
        try {

            if (isValid()) {  // Récupération des valeurs des champs
                int clientId = Integer.parseInt(clienttxt.getText());
                int vehiculeId = Integer.parseInt(vehiculetxt.getText());
                LocalDate localDate = datePicker.getValue();
                Date datefin = Date.valueOf(localDate);
                Calendar cal = Calendar.getInstance();
                Date dateDebut = new Date(cal.getTimeInMillis());
                int itineraireId = Integer.parseInt(itinerairetxt.getText());
                EtatReservation status = EtatReservation.en_cours;

                // Validation des champs de saisie de la réservation
                // Récupération des valeurs saisies dans les champs de saisie
                // Création de la réservation
                Reservation reservation = new Reservation(getReservation().getIdreservation(), dateDebut, datefin, vehiculeId, clientId, itineraireId, status);
                // Ajout de la réservation dans la base de données
                ReservationService reservationService = new ReservationService();
                if (reservationService.modifierReservation(reservation.getIdreservation(), reservation)) {
                    JOptionPane.showMessageDialog(null, "Reservation modifié!");
                }
            }
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
   

    public boolean isValid() {
        // Validation des champs de saisie de la réservation
        // Retourne true si tous les champs sont valides, false sinon
        if (clienttxt.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Champ Vide!");
            return false;
        }
        if (vehiculetxt.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Champ Vide!");
            return false;
        }

        if (itinerairetxt.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Champ Vide!");
              return false;
        }
    LocalDate localDate = datePicker.getValue();
            Date datefin = Date.valueOf(localDate);
            Calendar cal = Calendar.getInstance();
            Date dateDebut = new Date(cal.getTimeInMillis());
            if (datefin.before(dateDebut)) {
                JOptionPane.showMessageDialog(null, "La date de fin doit être postérieure à la date de réservation");
                return false;

            } else {
                return true;
            }}
    

    

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation res) {
        reservation = res;
        int idRes = res.getIdreservation();
        clienttxt.setText(Integer.toString(res.getIduser()));
        vehiculetxt.setText(Integer.toString(res.getIdvehicule()));
        itinerairetxt.setText(Integer.toString(res.getIditineraire()));
        datePicker.setValue(res.getDatefin().toLocalDate());
      
        }
                

    }

