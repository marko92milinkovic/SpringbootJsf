/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.domen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
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
@Table(name = "radnik", catalog = "baza", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Radnik.findAll", query = "SELECT r FROM Radnik r"),
    @NamedQuery(name = "Radnik.findByRadnikID", query = "SELECT r FROM Radnik r WHERE r.radnikID = :radnikID"),
    @NamedQuery(name = "Radnik.findByIme", query = "SELECT r FROM Radnik r WHERE r.ime = :ime"),
    @NamedQuery(name = "Radnik.findByPrezime", query = "SELECT r FROM Radnik r WHERE r.prezime = :prezime"),
    @NamedQuery(name = "Radnik.findByKorisnickoIme", query = "SELECT r FROM Radnik r WHERE r.korisnickoIme = :korisnickoIme"),
    @NamedQuery(name = "Radnik.findByKorisnickaSifra", query = "SELECT r FROM Radnik r WHERE r.korisnickaSifra = :korisnickaSifra"),
    @NamedQuery(name = "Radnik.findByAdresa", query = "SELECT r FROM Radnik r WHERE r.adresa = :adresa")})
public class Radnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RadnikID")
    private Integer radnikID;
    @Size(max = 20)
    @Column(name = "Ime")
    private String ime;
    @Size(max = 20)
    @Column(name = "Prezime")
    private String prezime;
    @Size(max = 10)
    @Column(name = "KorisnickoIme")
    private String korisnickoIme;
    @Size(max = 10)
    @Column(name = "KorisnickaSifra")
    private String korisnickaSifra;
    @Size(max = 40)
    @Column(name = "Adresa")
    private String adresa;
    @JoinColumn(name = "Mesto", referencedColumnName = "PttBroj")
    @ManyToOne
    private Mesto mesto;
    @OneToMany(mappedBy = "zaduzio")
    @JsonIgnore
    private List<Zaduzenje> zaduzenjeList;
    @OneToMany(mappedBy = "razduzio")
    @JsonIgnore
    private List<Zaduzenje> zaduzenjeList1;

    public Radnik() {
    }

    public Radnik(Integer radnikID) {
        this.radnikID = radnikID;
    }

    public Integer getRadnikID() {
        return radnikID;
    }

    public void setRadnikID(Integer radnikID) {
        this.radnikID = radnikID;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
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

    @XmlTransient
    public List<Zaduzenje> getZaduzenjeList() {
        return zaduzenjeList;
    }

    public void setZaduzenjeList(List<Zaduzenje> zaduzenjeList) {
        this.zaduzenjeList = zaduzenjeList;
    }

    @XmlTransient
    public List<Zaduzenje> getZaduzenjeList1() {
        return zaduzenjeList1;
    }

    public void setZaduzenjeList1(List<Zaduzenje> zaduzenjeList1) {
        this.zaduzenjeList1 = zaduzenjeList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (radnikID != null ? radnikID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Radnik)) {
            return false;
        }
        Radnik other = (Radnik) object;
        if ((this.radnikID == null && other.radnikID != null) || (this.radnikID != null && !this.radnikID.equals(other.radnikID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIme()+" "+getPrezime();
    }
    
}
