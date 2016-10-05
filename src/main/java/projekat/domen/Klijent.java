/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.domen;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author markom
 */
@Entity
@Table(name = "klijent", catalog = "baza", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klijent.findAll", query = "SELECT k FROM Klijent k"),
    @NamedQuery(name = "Klijent.findByKlijentID", query = "SELECT k FROM Klijent k WHERE k.klijentID = :klijentID"),
    @NamedQuery(name = "Klijent.findByIme", query = "SELECT k FROM Klijent k WHERE k.ime = :ime"),
    @NamedQuery(name = "Klijent.findByPrezime", query = "SELECT k FROM Klijent k WHERE k.prezime = :prezime"),
    @NamedQuery(name = "Klijent.findByJmbg", query = "SELECT k FROM Klijent k WHERE k.jmbg = :jmbg"),
    @NamedQuery(name = "Klijent.findByTelefon", query = "SELECT k FROM Klijent k WHERE k.telefon = :telefon"),
    @NamedQuery(name = "Klijent.findByMail", query = "SELECT k FROM Klijent k WHERE k.mail = :mail"),
    @NamedQuery(name = "Klijent.findByAdresa", query = "SELECT k FROM Klijent k WHERE k.adresa = :adresa")})
public class Klijent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KlijentID")
    private Integer klijentID;
    @Size(max = 20)
    @Column(name = "Ime")
    private String ime;
    @Size(max = 20)
    @Column(name = "Prezime")
    private String prezime;
    @Size(max = 13)
    @Column(name = "Jmbg")
    private String jmbg;
    @Size(max = 20)
    @Column(name = "Telefon")
    private String telefon;
    @Size(max = 30)
    @Column(name = "Mail")
    private String mail;
    @Size(max = 40)
    @Column(name = "Adresa")
    private String adresa;
    @JoinColumn(name = "Mesto", referencedColumnName = "PttBroj")
    @ManyToOne
    private Mesto mesto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klijent1", orphanRemoval = true)
    private List<Zaduzenje> zaduzenjeList;

    public Klijent() {
    }

    public Klijent(Integer klijentID) {
        this.klijentID = klijentID;
    }

    public Integer getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(Integer klijentID) {
        this.klijentID = klijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

//    @XmlTransient
    public List<Zaduzenje> getZaduzenjeList() {
        return zaduzenjeList;
    }

    public void setZaduzenjeList(List<Zaduzenje> zaduzenjeList) {
        this.zaduzenjeList = zaduzenjeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (klijentID != null ? klijentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klijent)) {
            return false;
        }
        Klijent other = (Klijent) object;
        if ((this.klijentID == null && other.klijentID != null) || (this.klijentID != null && !this.klijentID.equals(other.klijentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIme() + " " + getPrezime();
    }

}
