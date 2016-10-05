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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author markom
 */
@Entity
@Table(name = "mesto", catalog = "baza", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesto.findAll", query = "SELECT m FROM Mesto m"),
    @NamedQuery(name = "Mesto.findByPttBroj", query = "SELECT m FROM Mesto m WHERE m.pttBroj = :pttBroj"),
    @NamedQuery(name = "Mesto.findByNaziv", query = "SELECT m FROM Mesto m WHERE m.naziv = :naziv")})
public class Mesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PttBroj")
    private Integer pttBroj;
    @Size(max = 20)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(mappedBy = "mesto")
    @JsonIgnore
    private List<Radnik> radnikList;
    @OneToMany(mappedBy = "mesto")
    @JsonIgnore
    private List<Klijent> klijentList;

    public Mesto() {
    }

    public Mesto(Integer pttBroj) {
        this.pttBroj = pttBroj;
    }

    public Integer getPttBroj() {
        return pttBroj;
    }

    public void setPttBroj(Integer pttBroj) {
        this.pttBroj = pttBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Radnik> getRadnikList() {
        return radnikList;
    }

    public void setRadnikList(List<Radnik> radnikList) {
        this.radnikList = radnikList;
    }

    @XmlTransient
    public List<Klijent> getKlijentList() {
        return klijentList;
    }

    public void setKlijentList(List<Klijent> klijentList) {
        this.klijentList = klijentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pttBroj != null ? pttBroj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesto)) {
            return false;
        }
        Mesto other = (Mesto) object;
        if ((this.pttBroj == null && other.pttBroj != null) || (this.pttBroj != null && !this.pttBroj.equals(other.pttBroj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Mesto[ pttBroj=" + pttBroj + " ]";
    }
    
}
