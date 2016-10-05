/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.mb;

import projekat.domen.Klijent;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import org.springframework.stereotype.Component;
import projekat.domen.Radnik;

/**
 *
 * @author Marko
 */
//@ManagedBean
@SessionScoped
@Component
public class MbKlijent implements Serializable {

    private Klijent klijent;

    public MbKlijent() {
    }

    @PostConstruct
    private void init() {
//        log.debug("*** Init mbKlijent");
        klijent = new Klijent(-1);

    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

}
