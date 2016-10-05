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
import javax.persistence.JoinColumns;
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
@Table(name = "automobil", catalog = "baza", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Automobil.findAll", query = "SELECT a FROM Automobil a"),
    @NamedQuery(name = "Automobil.findByAutomobilID", query = "SELECT a FROM Automobil a WHERE a.automobilID = :automobilID"),
    @NamedQuery(name = "Automobil.findByRegistarskiBroj", query = "SELECT a FROM Automobil a WHERE a.registarskiBroj = :registarskiBroj"),
    @NamedQuery(name = "Automobil.findByBrojSasije", query = "SELECT a FROM Automobil a WHERE a.brojSasije = :brojSasije"),
    @NamedQuery(name = "Automobil.findByGodiste", query = "SELECT a FROM Automobil a WHERE a.godiste = :godiste"),
    @NamedQuery(name = "Automobil.findByKubikaza", query = "SELECT a FROM Automobil a WHERE a.kubikaza = :kubikaza")})
public class Automobil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AutomobilID")
    private Integer automobilID;
    @Size(max = 10)
    @Column(name = "RegistarskiBroj")
    private String registarskiBroj;
    @Size(max = 20)
    @Column(name = "BrojSasije")
    private String brojSasije;
    @Column(name = "Godiste")
    private Integer godiste;
    @Column(name = "Kubikaza")
    private Integer kubikaza;
    @JoinColumns({
        @JoinColumn(name = "Marka", referencedColumnName = "Marka"),
        @JoinColumn(name = "Model", referencedColumnName = "ModelID")})
    @ManyToOne
    private Model model;
    @OneToMany(mappedBy = "automobil")
    @JsonIgnore
    private List<Zaduzenje> zaduzenjeList;

    public Automobil() {
    }

    public Automobil(Integer automobilID) {
        this.automobilID = automobilID;
    }

    public Integer getAutomobilID() {
        return automobilID;
    }

    public void setAutomobilID(Integer automobilID) {
        this.automobilID = automobilID;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public String getBrojSasije() {
        return brojSasije;
    }

    public void setBrojSasije(String brojSasije) {
        this.brojSasije = brojSasije;
    }

    public Integer getGodiste() {
        return godiste;
    }

    public void setGodiste(Integer godiste) {
        this.godiste = godiste;
    }

    public Integer getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(Integer kubikaza) {
        this.kubikaza = kubikaza;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @XmlTransient
    public List<Zaduzenje> getZaduzenjeList() {
        return zaduzenjeList;
    }

    public void setZaduzenjeList(List<Zaduzenje> zaduzenjeList) {
        this.zaduzenjeList = zaduzenjeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (automobilID != null ? automobilID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Automobil)) {
            return false;
        }
        Automobil other = (Automobil) object;
        if ((this.automobilID == null && other.automobilID != null) || (this.automobilID != null && !this.automobilID.equals(other.automobilID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getModel()+" "+getRegistarskiBroj();
    }
    
}
