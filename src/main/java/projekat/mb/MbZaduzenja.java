/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.mb;

import projekat.domen.Zaduzenje;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import projekat.services.ZaduzenjeService;

/**
 *
 * @author Marko
 */
//@ManagedBean
@Scope("view")
@Service
public class MbZaduzenja {

    private List<Zaduzenje> zaduzenja;

    private Zaduzenje zaduzenje;

    private boolean izmena;

//    @ManagedProperty(value = "#{mbKlijent}")
    @Autowired
    private MbKlijent mbKlijent;

//    @ManagedProperty(value = "#{mbRadnik}")
    @Autowired
    private MbRadnik mbRadnik;

//    @EJB
//    SBZaduzenjeLocal sbZaduzenja;
    @Autowired
    ZaduzenjeService zaduzenjeService;

    public MbZaduzenja() {
    }

    @PostConstruct
    public void init() {
        System.out.println("Poziva se init");

//        if (mbRadnik == null || !mbRadnik.isUlogovan()) {
//            System.out.println("PROBLEM:"+mbRadnik== null);
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("/");
//            } catch (IOException ex) {
//                Logger.getLogger(MbUnosZaduzenja.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return;
//        }
        if (mbKlijent == null || mbKlijent.getKlijent() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("pretragaKlijenata_1.jsf");
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(MbUnosZaduzenja.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        zaduzenje = new Zaduzenje();
        try {
            zaduzenja = zaduzenjeService.vratiZaduzenjaKlijenta(mbKlijent.getKlijent());
            mbKlijent.getKlijent().setZaduzenjeList(zaduzenja);

//         RequestContext.getCurrentInstance().showMessageInDialog(msg);
        } catch (Exception ex) {
            Logger.getLogger(MbZaduzenja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Zaduzenje> pronadji() {
        FacesMessage msg;
        try {
            zaduzenja = zaduzenjeService.vratiZaduzenjaKlijenta(mbKlijent.getKlijent());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Poruka", "Zaduzenja su pronadjena");

        } catch (Exception ex) {
            ex.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Poruka", "Zaduzenja nisu pronadjena");

        }
        return zaduzenja;
    }

    public List<Zaduzenje> getZaduzenja() {
        return zaduzenja;
    }

    public void setZaduzenja(List<Zaduzenje> zaduzenja) {
        this.zaduzenja = zaduzenja;
    }

    public Zaduzenje getZaduzenje() {
        return zaduzenje;
    }

    public void setZaduzenje(Zaduzenje zaduzenje) {
        this.zaduzenje = zaduzenje;
    }

    public MbKlijent getMbKlijent() {
        return mbKlijent;
    }

    public void setMbKlijent(MbKlijent mbKlijent) {
        this.mbKlijent = mbKlijent;
    }

    public boolean isIzmena() {
        return izmena;
    }

    public void setIzmena(boolean izmena) {
        this.izmena = izmena;
    }

    public MbRadnik getMbRadnik() {
        return mbRadnik;
    }

    public void setMbRadnik(MbRadnik mbRadnik) {
        this.mbRadnik = mbRadnik;
    }

    public void omoguciIzmenu() {

    }

    public void sacuvajIzmene() {
        if (zaduzenje.getRazduzio() == null) {
            zaduzenje.setRazduzio(mbRadnik.getRadnik());
        }

        if (zaduzenje.getDatumDO() == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Greska", "Datum nije unet");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (zaduzenje.getDatumDO().before(zaduzenje.getDatumOD())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Greska", "Datum do mora biti pre datuma od");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            zaduzenje.setDatumDO(null);
        } else {
            try {
                zaduzenjeService.izmeniZaduzenje(zaduzenje);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Poruka", "Zaduzenje uspesno izmenjeno");
                RequestContext.getCurrentInstance().showMessageInDialog(msg);
            } catch (Exception e) {
                e.printStackTrace();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska",
                        "Sistem ne moze da zapamti izmene zaduzenja");
                RequestContext.getCurrentInstance().showMessageInDialog(msg);
            }
        }
    }
}
