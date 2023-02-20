/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.carngo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ychaa
 */
@Entity
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByIdreservation", query = "SELECT r FROM Reservation r WHERE r.idreservation = :idreservation"),
    @NamedQuery(name = "Reservation.findByDatedebut", query = "SELECT r FROM Reservation r WHERE r.datedebut = :datedebut")})
public class ReservationGene implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreservation")
    private Integer idreservation;
    @Basic(optional = false)
    @Column(name = "datedebut")
    @Temporal(TemporalType.DATE)
    private Date datedebut;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private Utilisateur iduser;
    @JoinColumn(name = "idvehicule", referencedColumnName = "idvehicule")
    @ManyToOne(optional = false)
    private VehiculeGene idvehicule;
    @JoinColumn(name = "iditineraire", referencedColumnName = "iditineraire")
    @ManyToOne(optional = false)
    private Itineraire iditineraire;

    public ReservationGene() {
    }

    public ReservationGene(Integer idreservation) {
        this.idreservation = idreservation;
    }

    public ReservationGene(Integer idreservation, Date datedebut) {
        this.idreservation = idreservation;
        this.datedebut = datedebut;
    }

    public Integer getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(Integer idreservation) {
        this.idreservation = idreservation;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Utilisateur getIduser() {
        return iduser;
    }

    public void setIduser(Utilisateur iduser) {
        this.iduser = iduser;
    }

    public VehiculeGene getIdvehicule() {
        return idvehicule;
    }

    public void setIdvehicule(VehiculeGene idvehicule) {
        this.idvehicule = idvehicule;
    }

    public Itineraire getIditineraire() {
        return iditineraire;
    }

    public void setIditineraire(Itineraire iditineraire) {
        this.iditineraire = iditineraire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreservation != null ? idreservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationGene)) {
            return false;
        }
        ReservationGene other = (ReservationGene) object;
        if ((this.idreservation == null && other.idreservation != null) || (this.idreservation != null && !this.idreservation.equals(other.idreservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.carngo.entities.Reservation[ idreservation=" + idreservation + " ]";
    }
    
}
