import entities.TypeVehicule;
import entities.Vehicule;
import services.VehiculeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {

    public static void main(String[] args) throws ParseException {

        //Voiture v=new Voiture(TypeVehicule.voiture,"fvgyhj",99,7,"9865 tun 223");
        //Voiture v2=new Voiture(TypeVehicule.voiture,"cc 00",178,5,"9463 tun 221");
        //VoitureService vs= new VoitureService();

        Vehicule v=new Vehicule(TypeVehicule.voiture,"sd 78",100,7,"9865 tun 235");
        Vehicule t=new Vehicule(TypeVehicule.trottinette,"hjk",100,30);
        VehiculeService vs= new VehiculeService();
        //vs.addEntity(v);
        //vs.addEntity(t);
        //vs.updateEntity(v);
        //vs.updateEntity(t);
        //vs.deleteEntity(v);
        //vs.deleteEntity(t);
        //System.out.println(vs.findAllEntity());
        //System.out.println(vs.findbylocation(null)); je peux la tester pour le moment sans implementer la classe itineraire
        //System.out.println(vs.findVoiturebymatricule("9865 tun 235"));

        SimpleDateFormat sd=new SimpleDateFormat("dd/mm/yyyy");
        Date date1=new Date("29/01/2023");
        Reclamation r=new Reclamation("test test",date1);
        ReclamationService rservice=new ReclamationService();
        rservice.ajouterReclamation(r);


    }
}
