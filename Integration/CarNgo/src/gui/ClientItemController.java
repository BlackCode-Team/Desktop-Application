package gui;

import entities.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.MyListener;
import utils.Mail;

import java.io.IOException;
import java.util.Optional;

public class ClientItemController {
    @FXML
    private ImageView img;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Button btnchoisir;
    @FXML
    private Label puissanceLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(v);
    }
    @FXML
    private Label id;
    private Vehicule v;
    private MyListener myListener;

    public void setDataC(Vehicule v, MyListener myListener) {
        this.v = v;
        this.myListener = myListener;
        nameLabel.setText(v.getModele());
        puissanceLabel.setText(String.valueOf(v.getPuissance()));
        prixLabel.setText(String.valueOf(v.getPrix()));
        //priceLabel.setText(String.valueOf(v.getBatterie()));
        try {
            Image image = new Image(v.getImg());
            img.setImage(image);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Handle error loading image
        }
    }

    public void choisir(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir choisit ce véhicule ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            notif();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReservation.fxml"));
            Parent root = loader.load();
            //  nouvelle scène avec le contenu de la scène Ajouter.fxml
            Scene scene = new Scene(root);
            // Obtenir la référence de la scène actuelle à partir du bouton "Ajouter"
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            // Afficher nouvelle scène
            AjouterReservationController ajouterreservationController = loader.getController();
            ajouterreservationController.setIdVehicule(v);
            ajouterreservationController.setIdItin(iditin);
            ajouterreservationController.setIduser(idUser);
            stage.setScene(scene);
            stage.show();}

        }

    public void notif(){
        if(v.getBatterie()<30)
            Mail.envoyer_batteriefaible(v);
    }

    int iditin;

int idUser;



    void setIduser(int idUser) {
        this.idUser = idUser;
        System.out.println("iduseraa " + idUser);
    }
    void setIdItin(int iditin) {
        this.iditin = iditin;
        System.out.println("iditneraireaa " + iditin);
    }


}
