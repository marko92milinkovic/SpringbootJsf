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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "marka", catalog = "baza", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marka.findAll", query = "SELECT m FROM Marka m"),
    @NamedQuery(name = "Marka.findByMarkaID", query = "SELECT m FROM Marka m WHERE m.markaID = :markaID"),
    @NamedQuery(name = "Marka.findByNaziv", query = "SELECT m FROM Marka m WHERE m.naziv = :naziv")})
public class Marka implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MarkaID")
    private Integer markaID;
    @Size(max = 20)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marka1")
    @JsonIgnore
    private List<Model> modelList;

    public Marka() {
    }

    public Marka(Integer markaID) {
        this.markaID = markaID;
    }

    public Integer getMarkaID() {
        return markaID;
    }

    public void setMarkaID(Integer markaID) {
        this.markaID = markaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Model> getModelList() {
        return modelList;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (markaID != null ? markaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marka)) {
            return false;
        }
        Marka other = (Marka) object;
        if ((this.markaID == null && other.markaID != null) || (this.markaID != null && !this.markaID.equals(other.markaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNaziv();
    }

}
