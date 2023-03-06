package gui;

import entities.TypeVehicule;
import entities.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.MyListener;
import services.VehiculeServices;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChoisirVehiculeController implements Initializable {
    @FXML
    private Label batterielabel;
    @FXML
    private Button btnsearch;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private ImageView carimg;
    @FXML
    private VBox chosenCarCard;
    @FXML
    private Label nbtrottinettes;
    @FXML
    private Label nbvoitures;
    @FXML
    private GridPane grid;
    @FXML
    private Label locationlabel;

    @FXML
    private Label matriculelabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label prixlabel;

    @FXML
    private Label puissancelabel;

    @FXML
    private Button btnchoisir;


    @FXML
    void choisir(ActionEvent event) {

    }

    @FXML
    private ScrollPane scroll;

    private List<Vehicule> vehicules = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    VehiculeServices vs = new VehiculeServices();

//    private void setChosenCar(Vehicule v) {
//        modelLabel.setText(v.getModele());
//        matriculelabel.setText( v.getMatricule());
//        batterielabel.setText(String.valueOf(v.getBatterie()));
//        puissancelabel.setText(String.valueOf(v.getPuissance()));
//        prixlabel.setText(String.valueOf(v.getPrix()));
//        Image image = new Image(v.getImg());
//        carimg.setImage(image);
//        chosenCarCard.setStyle("-fx-background-color: #80080C"  + ";\n" +
//                "    -fx-background-radius: 30;");
//    }
    @FXML
    private HBox btncar;
    @FXML
    private HBox btntrottinette;
    @FXML
    void affichertrot(MouseEvent event) {
        displayVehicules("TROTTINETTE");
    }
    @FXML
    void affichervoit(MouseEvent event) {
        displayVehicules("CAR");
    }
    private void displayVehicules(String vehiculeType) {
        vehicules.clear();
        vehicules.addAll(vs.findbyType(TypeVehicule.valueOf(vehiculeType)));

        grid.getChildren().clear(); // nettoyer la grille avant de la remplir à nouveau avec les nouveaux véhicules
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < vehicules.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ClientItemController itemController = fxmlLoader.getController();
                itemController.setDataC(vehicules.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button btnadd;
    @FXML
    private Button btnback;
    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    void add(ActionEvent event) throws IOException {
        // Charger FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterVehicule.fxml"));
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


    void consulter(List<Vehicule> vehicules){

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < vehicules.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/Clientitem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ClientItemController itemController = fxmlLoader.getController();
                itemController.setDataC(vehicules.get(i), myListener);
                itemController.setIdItin(iditin);
                itemController.setIduser(idUser);
                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nbvoitures.setText(String.valueOf(vs.calculTVehicule(TypeVehicule.voiture))+" Voitures");
        nbtrottinettes.setText(String.valueOf(vs.calculTVehicule(TypeVehicule.trottinette))+ " Trottinettes");

        vehicules.addAll(vs.findAllEntity());
        //vehicules.addAll(vs.findbylocation());
        consulter(vehicules);

        btncar.setOnMouseClicked(event -> {
            grid.getChildren().clear();
            vehicules.clear();
            List<Vehicule> cars = vs.findbyType(TypeVehicule.voiture);
            vehicules.addAll(cars);
            consulter(vehicules);
        });

        btntrottinette.setOnMouseClicked(event -> {
            grid.getChildren().clear();
            vehicules.clear();
            List<Vehicule> trottinettes = vs.findbyType(TypeVehicule.trottinette);
            vehicules.addAll(trottinettes);
            consulter(vehicules);
        });
        choicebox.getItems().addAll("Prix croissant", "Prix décroissant");
        // Ajouter un listener pour le changement de sélection
        choicebox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Appeler la méthode de tri en fonction de la valeur sélectionnée
            if ("Prix croissant".equals(newValue)) {
                List<Vehicule> listsearch=vs.afficherVehiculesTriesParPrix(TypeVehicule.voiture, true);
                consulter(listsearch); // Afficher la liste triée dans l'interface
            } else if ("Prix décroissant".equals(newValue)) {
                List<Vehicule> listsearch=vs.afficherVehiculesTriesParPrix(TypeVehicule.voiture, false);
                consulter(listsearch); // Afficher la liste triée dans l'interface
            }
        });

    }

    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("itineraire.fxml"));
        Parent root = loader.load();

        //  nouvelle scène avec le contenu de la scène Ajouter.fxml
        Scene scene = new Scene(root);

        // Obtenir la référence de la scène actuelle à partir du bouton "Ajouter"
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Afficher nouvelle scène
        stage.setScene(scene);
        stage.show();
    }

    int iditin;

    void setIdItin(int iditin) {
        this.iditin = iditin;
        System.out.println("iditneraire " + iditin);
    }
    int idUser;

    void setIduser(int idUser) {
        this.idUser = idUser;
        System.out.println("iduser " + idUser);
    }
}

