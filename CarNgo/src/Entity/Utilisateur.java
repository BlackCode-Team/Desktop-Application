/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Jokser
 */
public class Utilisateur {
    int idUser;
    String nom;
    String prenom;
    String password;
    String email;
    String cin;
    String permis;
    int idAffectationBadge;
    TypeUser Role;
//    enum TypeUser {Admin,Agent,Client};
    
    
//    public enum typeUser {
//        Administrateur, agent, client
//    };

    public Utilisateur() {
    }

    public Utilisateur(int idUser, String nom, String prenom, String password, String email, String cin, String permis, int idAffectationBadge, TypeUser Role) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.cin = cin;
        this.permis = permis;
        this.idAffectationBadge = idAffectationBadge;
        this.Role = Role;
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

    public int getIdAffectationBadge() {
        return idAffectationBadge;
    }

    public void setIdAffectationBadge(int idAffectationBadge) {
        this.idAffectationBadge = idAffectationBadge;
    }

    public TypeUser getRole() {
        return Role;
    }

    public void setRole(TypeUser Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", email=" + email + ", cin=" + cin + ", permis=" + permis + ", idAffectationBadge=" + idAffectationBadge + ", Role=" + Role + '}';
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 47 * hash + this.idUser;
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
        if (this.idUser != other.idUser) {
            return false;
        }
        return true;
    }

    

    
    
    
    
    
}
