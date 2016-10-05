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
import javax.validation.constraints.NotNull;

/**
 *
 * @author markom
 */
@Embeddable
public class ModelPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Marka")
    private int marka;
    @Basic(optional = false)
    @Column(name = "ModelID")
    private int modelID;

    public ModelPK() {
    }

    public ModelPK(int marka, int modelID) {
        this.marka = marka;
        this.modelID = modelID;
    }

    public int getMarka() {
        return marka;
    }

    public void setMarka(int marka) {
        this.marka = marka;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) marka;
        hash += (int) modelID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelPK)) {
            return false;
        }
        ModelPK other = (ModelPK) object;
        if (this.marka != other.marka) {
            return false;
        }
        if (this.modelID != other.modelID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return modelID+" "+marka;
    }
    
}
