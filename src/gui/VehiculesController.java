package gui;

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
    private Label nbtrottinettes;
    @FXML
    private Label nbvoitures;
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
    private HBox btncar;
    @FXML
    private HBox btntrottinette;

    @FXML
    List<Vehicule> search(ActionEvent event) {
        String critere = searchtxt.getText().toLowerCase();
        List<Vehicule> resultats = new ArrayList<>();
        if (critere.matches("^\\d{1,4}TUN\\d{3}$")) {
            resultats.add((Vehicule) vs.findbymatricule(critere));
        } else if (critere.equals("disponible")) {
            resultats.addAll(vs.findbyStatus(StatusVehicule.disponible));
        } else if (critere.equals("réservé")) {
            resultats.addAll(vs.findbyStatus(StatusVehicule.réservé));
        }
        if (!resultats.isEmpty()) {
            return resultats;
        } else {
            Alert al2=new Alert(Alert.AlertType.ERROR);
            al2.setTitle("recherche invalide");
            al2.setHeaderText("Pas de résultat ");
            al2.showAndWait();
            return new ArrayList<>();
        }
    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
        List<Vehicule> resultats = search(event);
        if (!resultats.isEmpty()) {
            ObservableList<Vehicule> vehiculesObservableList = FXCollections.observableArrayList(resultats);
            displayVehicules(resultats.get(0).getType().toString().toLowerCase());
        }
    }

    @FXML
    void update(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierVehicule.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // Obtenir la référence de la scène actuelle à partir du bouton "modifier"
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
        prixlabel.setText(String.valueOf(v.getPrix())+Main.CURRENCY);
        Image image = new Image(v.getImg());
        carimg.setImage(image);
        chosenCarCard.setStyle("-fx-background-color: #291D36"  + ";\n" +
                "    -fx-background-radius: 30;");
    }
    @FXML
    void affichertrot(MouseEvent event) {displayVehicules("trottinette");}
    @FXML
    void affichervoit(MouseEvent event) {displayVehicules("voiture");}
    private void displayVehicules(String vehiculeType) {
        vehicules.clear();
        vehicules.addAll(vs.findbyType(TypeVehicule.valueOf(vehiculeType)));

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
                fxmlLoader.setLocation(getClass().getResource("/gui/item.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterVehicule.fxml"));
        Parent root = loader.load();
        //  nouvelle scène avec le contenu de la scène AjouterVehicule.fxml
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
                fxmlLoader.setLocation(getClass().getResource("/gui/item.fxml"));
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
        nbvoitures.setText(String.valueOf(vs.calculTVehicule(TypeVehicule.voiture))+" Voitures");
        nbtrottinettes.setText(String.valueOf(vs.calculTVehicule(TypeVehicule.trottinette))+ " Trottinettes");
        chosenCarCard.setOnMouseClicked(event -> {
            // récupérez la voiture choisie et passez-la à votre méthode setChosenCar
            Vehicule vehiculeChoisi = (Vehicule) chosenCarCard.getUserData();
            //vehiculeChoisi.setColor("291D36");
            setChosenCar(vehiculeChoisi);
        });

        vehicules.addAll(vs.findAllEntity());
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
//        btnsearch.setOnAction(event ->  { ;
//            grid.getChildren().clear();
//            vehicules.clear();
//            List<Vehicule> cars = search(event);
//            vehicules.addAll(cars);
//            consulter(vehicules);});
//
//        }
    }}





