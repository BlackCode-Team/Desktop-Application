/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.carngo.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ychaa
 */
@Entity
@Table(name = "vehicule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehicule.findAll", query = "SELECT v FROM Vehicule v"),
    @NamedQuery(name = "Vehicule.findByIdvehicule", query = "SELECT v FROM Vehicule v WHERE v.idvehicule = :idvehicule"),
    @NamedQuery(name = "Vehicule.findByType", query = "SELECT v FROM Vehicule v WHERE v.type = :type"),
    @NamedQuery(name = "Vehicule.findByModele", query = "SELECT v FROM Vehicule v WHERE v.modele = :modele"),
    @NamedQuery(name = "Vehicule.findByBatterie", query = "SELECT v FROM Vehicule v WHERE v.batterie = :batterie"),
    @NamedQuery(name = "Vehicule.findByMatricule", query = "SELECT v FROM Vehicule v WHERE v.matricule = :matricule"),
    @NamedQuery(name = "Vehicule.findByPuissance", query = "SELECT v FROM Vehicule v WHERE v.puissance = :puissance"),
    @NamedQuery(name = "Vehicule.findByMaxspeed", query = "SELECT v FROM Vehicule v WHERE v.maxspeed = :maxspeed"),
    @NamedQuery(name = "Vehicule.findByEtatVehicule", query = "SELECT v FROM Vehicule v WHERE v.disponibilite = :disponibilite")})
public class VehiculeGene implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idvehicule")
    private Collection<Reservation> reservationCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvehicule")
    private Integer idvehicule;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Column(name = "modele")
    private String modele;
    @Column(name = "batterie")
    private Integer batterie;
    @Column(name = "matricule")
    private String matricule;
    @Column(name = "puissance")
    private Integer puissance;
    @Column(name = "maxspeed")
    private Integer maxspeed;
    @Column(name = "Disponibilite")
    private Boolean disponibilite;
    @JoinColumn(name = "iditineraire", referencedColumnName = "iditineraire")
    @ManyToOne(optional = false)
    private Itineraire iditineraire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idvehicule")
    private Collection<Reclamation> reclamationCollection;

    public VehiculeGene() {
    }

    public VehiculeGene(Integer idvehicule) {
        this.idvehicule = idvehicule;
    }

    public VehiculeGene(Integer idvehicule, String type) {
        this.idvehicule = idvehicule;
        this.type = type;
    }

    public Integer getIdvehicule() {
        return idvehicule;
    }

    public void setIdvehicule(Integer idvehicule) {
        this.idvehicule = idvehicule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Integer getBatterie() {
        return batterie;
    }

    public void setBatterie(Integer batterie) {
        this.batterie = batterie;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Integer getPuissance() {
        return puissance;
    }

    public void setPuissance(Integer puissance) {
        this.puissance = puissance;
    }

    public Integer getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(Integer maxspeed) {
        this.maxspeed = maxspeed;
    }

    public Boolean getEtatVehicule() {
        return disponibilite;
    }

    public void setEtatVehicule(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Itineraire getIditineraire() {
        return iditineraire;
    }

    public void setIditineraire(Itineraire iditineraire) {
        this.iditineraire = iditineraire;
    }

    @XmlTransient
    public Collection<Reclamation> getReclamationCollection() {
        return reclamationCollection;
    }

    public void setReclamationCollection(Collection<Reclamation> reclamationCollection) {
        this.reclamationCollection = reclamationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvehicule != null ? idvehicule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehiculeGene)) {
            return false;
        }
        VehiculeGene other = (VehiculeGene) object;
        if ((this.idvehicule == null && other.idvehicule != null) || (this.idvehicule != null && !this.idvehicule.equals(other.idvehicule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.carngo.entities.Vehicule[ idvehicule=" + idvehicule + " ]";
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }
    
}
