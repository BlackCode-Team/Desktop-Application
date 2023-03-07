package utils;


import entities.Reclamation;
import entities.Vehicule;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {


    private static String username = "benabdallah.rania@esprit.tn";
    private static String password = "hmybunyokirvpsce";

    public static void envoyer_batteriefaible(Vehicule v) {
        // Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //Set TLS encryption enabled
        props.put("mail.smtp.host", "smtp.gmail.com");  //Set SMTP host
        props.put("mail.smtp.port", "587"); //Set smtp port
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            //Etape2:Creation de l'objet de message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("benabdallah.rania@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("raniabenabdallah14@gmail.com"));
            message.setSubject("Attention au niveau de charge de " + v.getType());
            message.setText("Veuillez prendre note que la" + v.getType() + "louée, immatriculée " + v.getMatricule() +
                    " , est susceptible de tomber en panne sèche si un retard de deux heures est enregistré. ");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void envoyer_reponsereclamation(Reclamation r) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //Set TLS encryption enabled
        props.put("mail.smtp.host", "smtp.gmail.com");  //Set SMTP host
        props.put("mail.smtp.port", "587"); //Set smtp port
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            //Etape2:Creation de l'objet de message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("benabdallah.rania@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("raniabenabdallah14@gmail.com"));
            message.setSubject("Reclamation de date " + r.getDate());
            message.setText("Nous avons bien reçu votre réclamation déclarée le " + r.getDate() + "concernant le vehicule immatriculé " + r.getMatricule() +
                    " Nous sommes désolés que vous ayez rencontré ce problème et nous allons faire tout notre possible pour y remédier.");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}


