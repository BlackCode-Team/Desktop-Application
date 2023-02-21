package entities;

import java.util.Date;

public class Reclamation {
    private int id;
    private String contenu;
    private Date date;

    public Reclamation() {
    }


    public Reclamation(String contenu, Date date) {
        this.contenu = contenu;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "contenu='" + contenu + '\'' +
                ", date=" + date +
                '}';
    }
}
