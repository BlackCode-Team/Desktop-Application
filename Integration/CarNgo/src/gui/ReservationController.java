/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import entities.Reservation;
import entities.Utilisateur;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.Pdf;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author ychaa
 */
public class ReservationController extends Application implements Initializable {

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
    Button btnPayer;
    @FXML
    Button btnRetour;
    /**
     * Initializes the controller class.
     */
    int userId;
    ReservationService reservationService = new ReservationService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IDRes.setCellValueFactory(new PropertyValueFactory<>("idreservation"));
        IDVehicule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        IDItineraire.setCellValueFactory(new PropertyValueFactory<>("pointdepart"));
        IDItineraireFin.setCellValueFactory(new PropertyValueFactory<>("pointarrivee"));
        User.setCellValueFactory(new PropertyValueFactory<>("cin"));
        DateRes.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        DateFin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        // TableRes.setItems(reservationService.getReservationbyIdUserNDStatus(getUserId()));  
        //   TableRes.setItems(reservationService.getReservationbyIdUserNDStatus(userId));

        // System.out.println(user.getIdUser());
    }

    @FXML
    public void btnPayer(ActionEvent event) throws IOException, FileNotFoundException, DocumentException, SQLException, WriterException, com.google.zxing.WriterException {
        try {
           int idres= reservationService.getIdReservationbyIdUserNDStatus(userId);
            Pdf.pdfReservation(idres);
        } catch (IOException ex) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reservation.fxml"));
            Parent root = loader.load();

            ReservationController controller = loader.getController();
            controller.setUserId(userId);
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
    private int idVehicule;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        TableRes.setItems(reservationService.getReservationbyIdUserNDStatus(userId));

    }
    int iditin;

    void setIdItin(int iditin) {
        this.iditin = iditin;
        System.out.println("iditneraire " + iditin);
    }

}
