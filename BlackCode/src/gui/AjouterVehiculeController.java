package gui;

import entities.TypeVehicule;
import entities.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.VehiculeServices;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterVehiculeController implements Initializable {
    public static VehiculeServices vs=new VehiculeServices();
    @FXML
    private Button btnAdd;
    @FXML
    private ChoiceBox<TypeVehicule> choiceboxtype ;
    @FXML
    private TextField txtbatterie;
    @FXML
    private TextField txtmatricule;
    @FXML
    private TextField txtmodele;
    @FXML
    private TextField txtpuissance;
    @FXML
    private TextField txtprix;

    @FXML
    private Button btn;

    @FXML
    private Label label;
    @FXML
    private ImageView imgvw;
    @FXML
    public void handleChooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg","*.gif"),
                new FileChooser.ExtensionFilter("All Files", ".")
        );
        File selectedFile = fileChooser.showOpenDialog(((Node) event.getTarget()).getScene().getWindow());

        if (selectedFile != null) {
            String imagePath = selectedFile.toURI().toString();
            Image image = new Image(imagePath);
            label.setText(imagePath);
            imgvw.setImage(image);
            //imageField.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    void add(ActionEvent event) throws IOException{
        Vehicule v=new Vehicule();
        v.setType(choiceboxtype.getValue());
        v.setMatricule(txtmatricule.getText());
        v.setModele(txtmodele.getText());
        v.setPuissance(Integer.parseInt(txtpuissance.getText()));
        v.setBatterie(Integer.parseInt(txtbatterie.getText()));
        v.setPrix(Integer.parseInt(txtprix.getText()));
        if (txtmatricule.getText().isEmpty() || !txtmatricule.getText().matches("^\\d{1,4}TUN\\d{3}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie de matricule");
            alert.setHeaderText("La matricule saisi n'est pas valide");
            alert.setContentText("La matricule doit être au format XXXXTUNXXX, où X est un chiffre ");
            alert.showAndWait();
        }else if (!txtmodele.getText().matches("[a-zA-Z0-9]+")){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("erreur de saisie de modele");
            al.setHeaderText("Veuillez entrer le champs correspondant ");
            al.showAndWait();
        }else if (!txtpuissance.getText().matches("[0-9]+")|| (!txtbatterie.getText().matches("[0-9]+"))){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("champs vide");
            al.setHeaderText("champs vide! Veuillez entrer les champs manquants ");
            al.showAndWait();
        }else if (choiceboxtype.getValue() == null) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("champs vide");
            alert2.setHeaderText(" Veuillez choisir le type de véhicule");
            alert2.showAndWait();
        }else {
            vs.addEntity(v);
            Alert al2=new Alert(Alert.AlertType.CONFIRMATION);
            al2.setTitle("succes");
            al2.setHeaderText("vehicule ajouté !");
            al2.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDVehicule.fxml"));
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceboxtype.getItems().setAll(TypeVehicule.values());
        btnAdd.setOnAction(event -> {try {  add(event);
        } catch (IOException e) {
            e.printStackTrace();
        } });    }

    public void back(ActionEvent event) throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDVehicule.fxml"));
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
}
