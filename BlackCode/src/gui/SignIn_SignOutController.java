package gui;

import utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;


public class SignIn_SignOutController implements Initializable {

    @FXML
    private ImageView photoImage;
    @FXML
    private ImageView cnxLogo;
    @FXML
    private ImageView InscrLogo;
    @FXML
    private ImageView InscImage;
    @FXML
    private ImageView CnxImage;
    @FXML
    private TextField CnxAdresseEmail;

    @FXML
    private PasswordField CnxMotDePasse;

    @FXML
    private Button ConnexionBtn;

    @FXML
    private BorderPane ConnexionForm;

     @FXML
    private Button DirigInscription;

    @FXML
    private Button DirigeConnexion;

    @FXML
    private TextField InscAddresseEmail;

    @FXML
    private PasswordField InscMotDePasse;

    @FXML
    private TextField InscScannerCin;

    @FXML
    private TextField InscScannerPermis;

    @FXML
    private Button InscriptionBtn;

    @FXML
    private BorderPane InscriptionForm;

    private Connection cnx;
    private PreparedStatement prepare;
    private ResultSet result;

    public Connection myConnection() {
        return cnx = MyConnection.getInstance().getMyconnex();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Image image1 = new Image(getClass().getResourceAsStream("/images/LOGO APP2 transp.png"));
        cnxLogo.setImage(image1);
        
        Image image2 = new Image(getClass().getResourceAsStream("/images/04fcc65450efcccdc9869442c3e36310-1622972408.jpg"));
        CnxImage.setImage(image2);
        
        Image image3 = new Image(getClass().getResourceAsStream("/images/LOGO APP2 transp.png"));
        InscrLogo.setImage(image3);
        
        Image image4 = new Image(getClass().getResourceAsStream("/images/WhatsApp Image 2023-02-27 at 9.51.23 PM.jpeg"));
        InscImage.setImage(image4);
        
//        Image image5 = new Image(getClass().getResourceAsStream("/images/symbole-dinterface-de-camera-photo-pour-bouton.png"));
//        InscImage.setImage(image5);
        
//        Image image = new Image("@../../../../../PIDev/Maquette%20Xd/LOGO%20APP2%20transp.png");
//        cnxLogo.setImage(image);
    }

    @FXML
    void Connexion(ActionEvent event) {

        myConnection();
        String requete = "SELECT  `pwd`, `email` FROM `utilisateur` WHERE email= ? AND pwd = ?";
        try {
            if (CnxAdresseEmail.getText().isEmpty() || CnxMotDePasse.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vérifer les champs !");
            } else {
                prepare = cnx.prepareStatement(requete);
                prepare.setString(1, CnxAdresseEmail.getText());
                prepare.setString(2, CnxMotDePasse.getText());
                result = prepare.executeQuery();
                if (result.next()) {

                    JOptionPane.showMessageDialog(null, "Connexion réussite ! ");
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrecte ! ");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
    @FXML
    void Inscription (ActionEvent event) {
        myConnection();
        String requete ="INSERT INTO `utilisateur`( `email`,`pwd`, `cin`, `permis`) VALUES (?,?,?,?) ";
        try {
            if (InscMotDePasse.getText().isEmpty() || InscAddresseEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vérifer les champs !");
            } else {
                String checkData ="select `idUser` from `utilisateur` where `email`='"+InscAddresseEmail.getText()+"'";
                Statement ste =cnx.createStatement();
                result=ste.executeQuery(checkData);
                if (result.next()) {
                    JOptionPane.showMessageDialog(null, InscAddresseEmail +" is already used ! ");
                } else {
                    if (InscMotDePasse.getText().length()<8){
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un mot de passe qui dépasse 8 caractères  !");
                    } else {
                    prepare=cnx.prepareStatement(requete);
                    prepare.setString(1, InscAddresseEmail.getText());
                    prepare.setString(2, InscMotDePasse.getText());
                    prepare.setString(3, InscScannerCin.getText());
                    prepare.setString(4, InscScannerPermis.getText());
                    
                    prepare.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Compte crée avec succès !");
                }
                    
                }
                    }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
 @FXML
    void Switch (ActionEvent event) {
        if (event.getSource()==DirigeConnexion) {
            InscriptionForm.setVisible(true);
            ConnexionForm.setVisible(false);
        } else if (event.getSource()==DirigInscription) {
            InscriptionForm.setVisible(false);
            ConnexionForm.setVisible(true);
        }
    }
}
