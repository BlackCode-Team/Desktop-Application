package views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import main.MyListener;
import services.VehiculeServices;
import utils.Mail;

import java.util.Optional;

public class ItemController {
    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;
    @FXML
    private Label statuslabel;
    @FXML
    private Label id;
    private Vehicule v;
    private MyListener myListener;
    @FXML
    private ListView<Vehicule> listView;

    private ObservableList<Vehicule> listVehicules = FXCollections.observableArrayList();


    VehiculesController vc=new VehiculesController();

    public void setData(Vehicule v, MyListener myListener) {
        this.v = v;
        this.myListener = myListener;
        nameLabel.setText(v.getModele());
        statuslabel.setText(String.valueOf(v.getStatus()));
        notif();
        try {
            Image image = new Image(v.getImg());
            img.setImage(image);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Handle error loading image
        }

        // Create a VBox with TRASH icon
        VBox vbox = new VBox();
        vbox.setPrefHeight(29.0);
        vbox.setPrefWidth(51.0);

        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

        // Set the style properties of deleteIcon
        deleteIcon.setStyle(
                "-fx-cursor: hand;"
                        + "-glyph-size:24px;"
                        + "-fx-fill:#ff1744;"
                        + "-fx-border-insets: 5px;"
                        + "-fx-padding: 10px;"
        );

        // Add event handler to deleteIcon
        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
            // Code to delete the Vehicule object associated with this item
        });

        vbox.getChildren().add(deleteIcon);
    }

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(v);

    }

    public void onDeleteListener() {
        // Supprimer la carte correspondante de la liste affichée
        listVehicules.remove(v);
        listView.getItems().clear();
        listView.getItems().addAll(listVehicules);
    }

    public void notif(){
        if(v.getBatterie()<30)
            Mail.envoyer_batteriefaible(v);
    }


    @FXML
    private void deleteClicked(MouseEvent event) {
        // Demander confirmation avant de supprimer
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce véhicule ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            // Appeler la méthode deleteEntity de la classe VehiculeService pour supprimer le véhicule
            VehiculeServices vs = new VehiculeServices();
            boolean isDeleted = vs.deleteEntity(v);
            listVehicules.remove(v);
            vc.refreshGrid();

        }
    }

}


