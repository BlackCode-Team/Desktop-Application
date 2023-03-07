package main;

import entities.Reservation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.HistoriqueService;
import services.ReclamationService;


public class Main extends Application {

    public static final String CURRENCY = "$";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Vehicules.fxml"));
        primaryStage.setTitle("consultation vehicule");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
     launch(args);
//        Reservation r=new Reservation()
//        "2023-03-10", "2023-03-15", "250", "en cours", "90", 1, "en cours");
//       ReclamationService rs=new ReclamationService();
//        System.out.println(rs.calculReclamation());
       // System.out.println(rs.afficherentiteClient());
        //System.out.println(rs.afficherentite());
//        HistoriqueService hs=new HistoriqueService();
//        System.out.println(hs.afficherentiteAgent());
       // System.out.println(rs.afficherVehiculesReservees(1));
       // System.out.println(rs.afficherentite());
//      Vehicule v=new Vehicule(TypeVehicule.trottinette,"rania",85,85,"123TUN525",5);
//    VehiculeServices vs=new VehiculeServices();
//        System.out.println(vs.afficherVehiculesTriesParPrix(TypeVehicule.voiture,false));
//        System.out.println(vs.findAllEntity());
//      vs.addEntity(v);
        //System.out.println(vs.calculConsommationEnergie(v,1));
        //System.out.println(vs.findAllEntity());

   // vs.addEntity(v);
//        vs.deleteEntity(v);
//        System.out.println(vs.findbyType(TypeVehicule.trottinette));
//        System.out.println(v.getType());
//        System.out.println(vs.findbymatricule("8095TUN203"));
   }
}
