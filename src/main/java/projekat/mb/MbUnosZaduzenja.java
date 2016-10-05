/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.mb;

import java.io.IOException;
import projekat.domen.Zaduzenje;
import projekat.domen.ZaduzenjePK;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import projekat.ClientLog;
import projekat.services.ZaduzenjeService;

/**
 *
 * @author Marko
 */
//@ManagedBean
//@ViewScoped
@Scope("view")
@Service
public class MbUnosZaduzenja {

//    @EJB
//    SBZaduzenjeLocal sbZaduzenja;
    @Autowired
    ZaduzenjeService zaduzenjeService;

    private List<Zaduzenje> listaZaduzenja;

    private Zaduzenje zaduzenje;
    int rbr = 0;

//    @ManagedProperty(value = "#{mbIzborAutomobila}")
    @Autowired
    private MbIzborAutomobila mbIzborAutomobila;

//    @ManagedProperty(value = "#{mbRadnik}")
    @Autowired
    private MbRadnik mbRadnik;

//    @ManagedProperty(value = "#{mbKlijent}")
    @Autowired
    private MbKlijent mbKlijent;

    ClientLog log = new ClientLog(getClass());

    public MbUnosZaduzenja() {

    }

    @PostConstruct
    private void init() {
        log.debug("***Init");
        int idklijent = 0;
        listaZaduzenja = new ArrayList<>();
//        if (mbRadnik == null || mbRadnik.getRadnik() == null || mbRadnik.getRadnik().getIme() == null) {
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("/");
//                return;
//            } catch (IOException ex) {
//                Logger.getLogger(MbUnosZaduzenja.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }

        if (mbKlijent == null || mbKlijent.getKlijent() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("pretragaKlijenata_1.jsf");
                return;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            idklijent = mbKlijent
                    .getKlijent().
                    getKlijentID();
        }
        try {
            rbr = zaduzenjeService.getMaxValueOfRbr(mbKlijent.getKlijent());
        } catch (Exception ex) {

        }
        zaduzenje = new Zaduzenje(idklijent, rbr);
    }

    public List<Zaduzenje> getListaZaduzenja() {
        return listaZaduzenja;
    }

    public void setListaZaduzenja(List<Zaduzenje> listaZaduzenja) {
        this.listaZaduzenja = listaZaduzenja;
    }

    public void dodajZaduzenje() {
        System.out.println("ubacujem zaduzenje");
        System.out.println(TimeZone.getDefault());

        if (zaduzenje.getDatumOD() == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Datum nije unet", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        if (mbIzborAutomobila.getAutomobil() == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Automobil nije izabran", "Automobil nije izabran");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        zaduzenje.
                setAutomobil(
                        mbIzborAutomobila.
                        getAutomobil());

        zaduzenje.setZaduzio(
                mbRadnik.
                getRadnik());
        zaduzenje.setZaduzenjePK(new ZaduzenjePK(mbKlijent.getKlijent().getKlijentID(), ++rbr));
        listaZaduzenja.add(zaduzenje);

        mbIzborAutomobila.getListaAutomobila().remove(zaduzenje.getAutomobil());
        mbIzborAutomobila.getFiltriraniAutomobili().remove(zaduzenje.getAutomobil());
        zaduzenje = new Zaduzenje(mbKlijent.getKlijent().getKlijentID(), -1);

    }

    public MbIzborAutomobila getMbIzborAutomobila() {
        return mbIzborAutomobila;
    }

    public void setMbIzborAutomobila(MbIzborAutomobila mbIzborAutomobila) {
        this.mbIzborAutomobila = mbIzborAutomobila;
    }

    public Zaduzenje getZaduzenje() {
        return zaduzenje;
    }

    public void setZaduzenje(Zaduzenje zaduzenje) {
        this.zaduzenje = zaduzenje;
    }

    public MbRadnik getMbRadnik() {
        return mbRadnik;
    }

    public void setMbRadnik(MbRadnik mbRadnik) {
        this.mbRadnik = mbRadnik;
    }

    public MbKlijent getMbKlijent() {
        return mbKlijent;
    }

    public void setMbKlijent(MbKlijent mbKlijent) {
        this.mbKlijent = mbKlijent;
    }

    public void zapamtiZaduzenja() {

        System.out.println("Treba zpamtiti: " + listaZaduzenja);

        if (listaZaduzenja.isEmpty()) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Niste dodali zaduzenja", "Niste dodali zaduzenja"));
            return;
        }

        FacesMessage msg;
        try {
            zaduzenjeService.sacuvajZaduzenja(listaZaduzenja);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Poruka", "Sistem je zapamtio zaduzenja");
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska",
                    "Sistem ne moze da zapamti nova zaduzenja");
            e.printStackTrace();
            System.out.println(e);

        }
        RequestContext.getCurrentInstance().showMessageInDialog(msg);
        listaZaduzenja = new ArrayList<>();
        rbr = 0;
    }
}
