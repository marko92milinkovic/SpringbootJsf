/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.mb;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author marko
 */
@Service
public class RedirectBean {

    @Autowired
    MbKlijent mbKlijent;

    public void checkSelected() throws IOException {
        System.out.println("adasdsadsdsa");
        if (mbKlijent.getKlijent().getKlijentID()<0) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/secure/pretragaKlijenata_1.xhtml");
        }
    }

    @Autowired
    MbRadnik mbRadnik;

    public void checkAlreadyLoggedin() throws IOException {
        if (mbRadnik.isUlogovan()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/secure/index.xhtml");
        }
    }
}
