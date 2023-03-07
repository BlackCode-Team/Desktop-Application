package utils;

import entities.Vehicule;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class syrine {


    private static String username = "syrine.rebai@esprit.tn";
    private static String password = "7ot mot de passek";

    public void sendEmail(String recipient){
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
            message.setFrom(new InternetAddress("sirine.rebai@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("sirinerebai1234@gmail.com"));
            Transport.send(message);
            message.setSubject("Ekteb l'objet mtaa l mail ");
            message.setText("w lenna lcontenu ");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
