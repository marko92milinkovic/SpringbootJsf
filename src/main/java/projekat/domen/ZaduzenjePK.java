/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author markom
 */
@Embeddable
public class ZaduzenjePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Klijent")
    private int klijent;
    @Basic(optional = false)
    @Column(name = "RBZaduzenja")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rBZaduzenja;

    public ZaduzenjePK() {
    }

    public ZaduzenjePK(int klijent, int rBZaduzenja) {
        this.klijent = klijent;
        this.rBZaduzenja = rBZaduzenja;
    }

    public int getKlijent() {
        return klijent;
    }

    public void setKlijent(int klijent) {
        this.klijent = klijent;
    }

    public int getRBZaduzenja() {
        return rBZaduzenja;
    }

    public void setRBZaduzenja(int rBZaduzenja) {
        this.rBZaduzenja = rBZaduzenja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) klijent;
        hash += (int) rBZaduzenja;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZaduzenjePK)) {
            return false;
        }
        ZaduzenjePK other = (ZaduzenjePK) object;
        if (this.klijent != other.klijent) {
            return false;
        }
        if (this.rBZaduzenja != other.rBZaduzenja) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.ZaduzenjePK[ klijent=" + klijent + ", rBZaduzenja=" + rBZaduzenja + " ]";
    }
    
}
