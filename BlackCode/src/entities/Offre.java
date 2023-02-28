package entities;

public class Offre {
    private int idoffre;
    private String nom;
    private String description;
    private int idbadge;
    private Badge badge;
    private String typebadge;

    public String getTypebadge() {
        return typebadge;
    }

    public void setIdoffre(int idoffre) {
        this.idoffre = idoffre;
    }

    public Offre(int idoffre, String nom, String description, String typebadge) {
        this.idoffre = idoffre;
        this.nom = nom;
        this.description = description;
        this.typebadge = typebadge;
    }

    public void setTypebadge(String typebadge) {
        this.typebadge = typebadge;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
    
    public Offre() {
    }

    public Offre(String nom, String description, int idbadge) {
        this.nom = nom;
        this.description = description;
        this.idbadge = idbadge;
    }

    public Offre(int idoffre, String nom, String description, int idbadge) {
        this.idoffre = idoffre;
        this.nom = nom;
        this.description = description;
        this.idbadge = idbadge;
    }
    

    public int getIdoffre() {
        return idoffre;
    }

//    public void setIdoffre(int idoffre) {
//        this.idoffre = idoffre;
//    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdbadge() {
        return idbadge;
    }

    public void setIdbadge(int idbadge) {
        this.idbadge = idbadge;
    }

    @Override
    public String toString() {
        return "Offre{" + "idoffre=" + idoffre + ", nom=" + nom + ", description=" + description + ", Type Badge =" + typebadge + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idoffre;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offre other = (Offre) obj;
        if (this.idoffre != other.idoffre) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
