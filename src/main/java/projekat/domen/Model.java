/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.domen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "model", catalog = "baza", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Model.findAll", query = "SELECT m FROM Model m"),
    @NamedQuery(name = "Model.findByMarka", query = "SELECT m FROM Model m WHERE m.modelPK.marka = :marka"),
    @NamedQuery(name = "Model.findByModelID", query = "SELECT m FROM Model m WHERE m.modelPK.modelID = :modelID"),
    @NamedQuery(name = "Model.findByNaziv", query = "SELECT m FROM Model m WHERE m.naziv = :naziv")})
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ModelPK modelPK;
    @Size(max = 20)
    @Column(name = "Naziv")
    private String naziv;
    @JoinColumn(name = "Marka", referencedColumnName = "MarkaID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Marka marka1;
    @OneToMany(mappedBy = "model")
    @JsonIgnore
    private List<Automobil> automobilList;

    public Model() {
    }

    public Model(ModelPK modelPK) {
        this.modelPK = modelPK;
    }

    public Model(int marka, int modelID) {
        this.modelPK = new ModelPK(marka, modelID);
    }

    public ModelPK getModelPK() {
        return modelPK;
    }

    public void setModelPK(ModelPK modelPK) {
        this.modelPK = modelPK;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Marka getMarka1() {
        return marka1;
    }

    public void setMarka1(Marka marka1) {
        this.marka1 = marka1;
    }

    @XmlTransient
    public List<Automobil> getAutomobilList() {
        return automobilList;
    }

    public void setAutomobilList(List<Automobil> automobilList) {
        this.automobilList = automobilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelPK != null ? modelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Model)) {
            return false;
        }
        Model other = (Model) object;
        if ((this.modelPK == null && other.modelPK != null) || (this.modelPK != null && !this.modelPK.equals(other.modelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNaziv();
    }
    
}
