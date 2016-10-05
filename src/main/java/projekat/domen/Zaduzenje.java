/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.domen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author markom
 */
@Entity
@Table(name = "zaduzenje", catalog = "baza", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaduzenje.findAll", query = "SELECT z FROM Zaduzenje z"),
    @NamedQuery(name = "Zaduzenje.findByKlijent", query = "SELECT z FROM Zaduzenje z WHERE z.zaduzenjePK.klijent = :klijent"),
    @NamedQuery(name = "Zaduzenje.findByRBZaduzenja", query = "SELECT z FROM Zaduzenje z WHERE z.zaduzenjePK.rBZaduzenja = :rBZaduzenja"),
    @NamedQuery(name = "Zaduzenje.findByDatumOD", query = "SELECT z FROM Zaduzenje z WHERE z.datumOD = :datumOD"),
    @NamedQuery(name = "Zaduzenje.findByDatumDO", query = "SELECT z FROM Zaduzenje z WHERE z.datumDO = :datumDO")})
public class Zaduzenje implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ZaduzenjePK zaduzenjePK;
    @Column(name = "DatumOD")
    @Temporal(TemporalType.DATE)
    private Date datumOD;
    @Column(name = "DatumDO")
    @Temporal(TemporalType.DATE)
    private Date datumDO;
    @JoinColumn(name = "Klijent", referencedColumnName = "KlijentID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private Klijent klijent1;
    @JoinColumn(name = "Automobil", referencedColumnName = "AutomobilID")
    @ManyToOne
    private Automobil automobil;
    @JoinColumn(name = "Zaduzio", referencedColumnName = "RadnikID")
    @ManyToOne
    private Radnik zaduzio;
    @JoinColumn(name = "Razduzio", referencedColumnName = "RadnikID")
    @ManyToOne
    private Radnik razduzio;

    public Zaduzenje() {
    }

    public Zaduzenje(ZaduzenjePK zaduzenjePK) {
        this.zaduzenjePK = zaduzenjePK;
    }

    public Zaduzenje(int klijent, int rBZaduzenja) {
        this.zaduzenjePK = new ZaduzenjePK(klijent, rBZaduzenja);
    }

    public ZaduzenjePK getZaduzenjePK() {
        return zaduzenjePK;
    }

    public void setZaduzenjePK(ZaduzenjePK zaduzenjePK) {
        this.zaduzenjePK = zaduzenjePK;
    }

    public Date getDatumOD() {
        return datumOD;
    }

    public void setDatumOD(Date datumOD) {
        this.datumOD = datumOD;
    }

    public Date getDatumDO() {
        return datumDO;
    }

    public void setDatumDO(Date datumDO) {
        this.datumDO = datumDO;
    }

    public Klijent getKlijent1() {
        return klijent1;
    }

    public void setKlijent1(Klijent klijent1) {
        this.klijent1 = klijent1;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    public Radnik getZaduzio() {
        return zaduzio;
    }

    public void setZaduzio(Radnik zaduzio) {
        this.zaduzio = zaduzio;
    }

    public Radnik getRazduzio() {
        return razduzio;
    }

    public void setRazduzio(Radnik razduzio) {
        this.razduzio = razduzio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zaduzenjePK != null ? zaduzenjePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaduzenje)) {
            return false;
        }
        Zaduzenje other = (Zaduzenje) object;
        if ((this.zaduzenjePK == null && other.zaduzenjePK != null) || (this.zaduzenjePK != null && !this.zaduzenjePK.equals(other.zaduzenjePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Zaduzenje{" + "zaduzenjePK=" + zaduzenjePK + ", datumOD=" + datumOD + ", datumDO=" + datumDO + ", klijent1=" + klijent1 + ", automobil=" + automobil + ", zaduzio=" + zaduzio + ", razduzio=" + razduzio + '}';
    }

}
