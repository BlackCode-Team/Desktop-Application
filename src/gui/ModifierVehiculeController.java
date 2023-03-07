package gui;

import entities.TypeVehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import services.VehiculeServices;

import java.io.IOException;

public class ModifierVehiculeController {
    @FXML
    private Button btn;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnUpdate;
    @FXML
    private ChoiceBox<TypeVehicule> choiceboxtype;
    @FXML
    private ImageView imgvw;
    @FXML
    private Label label;
    @FXML
    private TextField txtbatterie;

    @FXML
    private TextField txtmatricule;

    @FXML
    private TextField txtmodele;

    @FXML
    private TextField txtprix;

    @FXML
    private TextField txtpuissance;

    VehiculeServices vs = new VehiculeServices();

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void handleChooseImage(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) throws IOException {
    }
}


//            // Retrieve the selected entity from the data store
//            String matricule = v.getselectedindex().v.getMatricule();
//            Vehicule selectedVehicule = (Vehicule) vs.findbymatricule(matricule);
//
//            // Update the entity with new values
//            selectedVehicule.setType(choiceboxtype.getValue());
//            selectedVehicule.setMatricule(txtmatricule.getText());
//            selectedVehicule.setModele(txtmodele.getText());
//            selectedVehicule.setPuissance(Integer.parseInt(txtpuissance.getText()));
//            selectedVehicule.setBatterie(Integer.parseInt(txtbatterie.getText()));
//            selectedVehicule.setPrix(Integer.parseInt(txtprix.getText()));
//
//            // Save the changes to the data store
//            vs.updateEntity(selectedVehicule);
//
//            // Show a confirmation message
//            Alert al2 = new Alert(Alert.AlertType.CONFIRMATION);
//            al2.setTitle("Succès");
//            al2.setHeaderText("Véhicule modifié !");
//            al2.showAndWait();
//
//            // Load the CRUDVehicule.fxml scene
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Vehicules.fxml"));
//            Parent root = loader.load();
//
//            // Create a new scene with the contents of the CRUDVehicule.fxml scene
//            Scene scene = new Scene(root);
//
//            // Get the reference of the current scene from the Modify button
//            Node source = (Node) event.getSource();
//            Stage stage = (Stage) source.getScene().getWindow();
//
//            // Show the new scene
//            stage.setScene(scene);
//            stage.show();
//        }
//
//    }
//}
