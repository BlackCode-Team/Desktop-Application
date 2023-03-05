package views;

import entities.StatusVehicule;
import entities.TypeVehicule;
import entities.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import main.Main;
import main.MyListener;
import services.VehiculeServices;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VehiculesController implements Initializable {

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
    private ScrollPane scroll;

    @FXML
    private TextField searchtxt;

    @FXML
    void search(ActionEvent event) {
        String critere= searchtxt.getText();
        switch (critere){
            case "voiture"  :
                displayVehicules("voiture");
                break;
            case "trottinette":
                displayVehicules("trottinette");

                break;
            case "disponible":
                consulter(vs.findbyStatus(StatusVehicule.disponible));
                break;
            case "réservé":
                consulter(vs.findbyStatus(StatusVehicule.réservé));
                break;
            default:
                if (critere.matches("^\\d{1,4}TUN\\d{3}$")) {
                    consulter(vs.findbymatricule(critere));
                } else {
                    // Afficher "pas de résultat" dans la grid
                    Label label = new Label("Pas de résultat");
                    grid.add(label, 0, 0);
                }
        }
    }

//    @FXML
//    private void deletev(ActionEvent event) throws IOException {
//        Vehicule v = (Vehicule) chosenCarCard.getUserData();
//        // ou toute autre manière de récupérer la Vehicule sélectionnée
//        int index = grid.getChildren().indexOf(chosenCarCard);
//        grid.getChildren().remove(chosenCarCard);
//        vs.deleteEntity(v);
//
//    }
//

    @FXML
    void update(ActionEvent event) throws IOException {
        // Charger FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterVehicule.fxml"));
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

    private List<Vehicule> vehicules = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    VehiculeServices vs = new VehiculeServices();

    private void setChosenCar(Vehicule v) {
        modelLabel.setText(v.getModele());
        matriculelabel.setText( v.getMatricule());
        batterielabel.setText(String.valueOf(v.getBatterie()));
        puissancelabel.setText(String.valueOf(v.getPuissance()));
        prixlabel.setText(String.valueOf(v.getPrix()));
        Image image = new Image(v.getImg());
        carimg.setImage(image);
        chosenCarCard.setStyle("-fx-background-color: #291D36"  + ";\n" +
                "    -fx-background-radius: 30;");
    }
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
        if (vehiculeType.equals("CAR")) {
            vehicules.addAll(vs.findbyType(TypeVehicule.voiture));
        } else if (vehiculeType.equals("TROTTINETTE")) {
            vehicules.addAll(vs.findbyType(TypeVehicule.trottinette));
        }

        if (vehicules.size() > 0) {
            setChosenCar(vehicules.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Vehicule v) {
                    setChosenCar(v);
                }
            };
        }
        grid.getChildren().clear(); // nettoyer la grille avant de la remplir à nouveau avec les nouveaux véhicules
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < vehicules.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(vehicules.get(i), myListener);

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
    void add(ActionEvent event) throws IOException {
        // Charger FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterVehicule.fxml"));
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
        if (vehicules.size() > 0) {
            setChosenCar(vehicules.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Vehicule v) {
                    setChosenCar(v);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < vehicules.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(vehicules.get(i), myListener);

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
    private ListView<Vehicule> listView;

    private ObservableList<Vehicule> listVehicules = FXCollections.observableArrayList();

    public void refreshGrid() {
        listView.getItems().clear();
        listView.getItems().addAll(vs.findAllEntity());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chosenCarCard.setOnMouseClicked(event -> {
            // récupérez la voiture choisie et passez-la à votre méthode setChosenCar
            Vehicule vehiculeChoisi = (Vehicule) chosenCarCard.getUserData();
            //vehiculeChoisi.setColor("291D36");
            setChosenCar(vehiculeChoisi);
        });

        vehicules.addAll(vs.findAllEntity());
        consulter(vehicules);

        btncar.setOnMouseClicked(event -> {
            vehicules.clear();
            List<Vehicule> cars = vs.findbyType(TypeVehicule.voiture);
            vehicules.addAll(cars);
            consulter(vehicules);
        });

        btntrottinette.setOnMouseClicked(event -> {
            vehicules.clear();
            List<Vehicule> trottinettes = vs.findbyType(TypeVehicule.trottinette);
            vehicules.addAll(trottinettes);
            consulter(vehicules);
        });}
}
