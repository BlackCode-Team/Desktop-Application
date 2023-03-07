package entities;


import java.util.Objects;

public class Utilisateur {
    int idUser;
    String nom;
    String prenom;
    String password;
    String email;
    String cin;
    String permis;
    int nbPoint;
//    int idAffectationBadge;
    TypeUser Role;
//    enum TypeUser {Admin,Agent,Client};
    
    
//    public enum typeUser {
//        Administrateur, agent, client
//    };

    public Utilisateur() {
    }

    public Utilisateur( String nom, String prenom, String password, String email, String cin, String permis, int nbPoint, TypeUser Role) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.cin = cin;
        this.permis = permis;
        this.nbPoint = nbPoint;
//        this.idAffectationBadge = idAffectationBadge;
        this.Role = Role;
    }
     public Utilisateur( String nom, String prenom, String password, String email, String cin, String permis, int nbPoint) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.cin = cin;
        this.permis = permis;
        this.nbPoint = nbPoint;
//       
    }

    public Utilisateur(String nom, String prenom, String email, String cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.cin = cin;
    }
    
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public int getNbPoint() {
        return nbPoint;
    }

    public void setNbPoint(int nbPoint) {
        this.nbPoint = nbPoint;
    }

//    public int getIdAffectationBadge() {
//        return idAffectationBadge;
//    }
//
//    public void setIdAffectationBadge(int idAffectationBadge) {
//        this.idAffectationBadge = idAffectationBadge;
//    }

    public TypeUser getRole() {
        return Role;
    }

    public void setRole(TypeUser Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", email=" + email + ", cin=" + cin + ", permis=" + permis + ", nbPoint=" + nbPoint + ", Role=" + Role + '}';
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 23 * hash + this.idUser;
//        hash = 23 * hash + Objects.hashCode(this.cin);
//        return hash;
//    }

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
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        return true;
    }

     public int CalculerNbPoints(int paiement) {
         
        nbPoint=0;
        if (Role==Role.client)
        if (paiement >= 100) {
            nbPoint = (int) paiement / 100 * 10;
        } else if (paiement >= 50) {
            paiement = (int) paiement / 50 * 5;
        } else {
            paiement = (int) paiement / 10;
        }
        return nbPoint;
    }
    
    
}
