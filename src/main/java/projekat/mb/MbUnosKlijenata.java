/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.mb;

import projekat.domen.Klijent;
import projekat.domen.Mesto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import projekat.domen.Radnik;
import projekat.domen.Zaduzenje;
import projekat.services.KlijentService;
import projekat.services.MestoService;

/**
 *
 * @author Marko
 */
//@ManagedBean
@Scope("view")
@Service
public class MbUnosKlijenata {

//    @EJB
//    SBKlijentLocal sbKlijent;
//
//    @EJB
//    SBMestoLocal sbMesto;
    @Autowired
    @Qualifier("klijentServiceRESTImpl")
    KlijentService klijentService;

    @Autowired
    @Qualifier("mestoServiceRestImpl")
    MestoService mestoService;

    private List<Mesto> listaMesta;

    // private List<Klijent> listaKlijenta;
    private List<Klijent> filtriraniKlijenti;

    private String filter;

    Klijent klijent;
    private boolean izmena;

    public MbUnosKlijenata() {

    }

    @PostConstruct
    public void pripremiPolja() {
        filtriraniKlijenti = new ArrayList<>();
        klijent = new Klijent();
        try {
            listaMesta = mestoService.vratiSvaMesta();
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska", "Mesta nisu ucitana");

            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
//        try {
//            filtriraniKlijenti = sbKlijent.pronadjiKlijente("");
//        } catch (Exception ex) {
//              FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska", "Klijenti nisu ucitani");
//
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//        }

    }

    public List<Mesto> getListaMesta() {
        return listaMesta;
    }

    public void setListaMesta(List<Mesto> listaMesta) {
        this.listaMesta = listaMesta;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public void sacuvaj() {
        FacesMessage message;
        System.out.println("Pocinjem da cuvam");
        try {
            klijentService.sacuvaj(klijent);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Poruka", "Klijent upesno zapamcen");
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", ex.getMessage());
        }

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void obrisiKlijenta(Klijent klijent) {

        FacesMessage message;

        try {
            klijentService.obrisi(klijent);
            filtriraniKlijenti.remove(klijent);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Greska", "Klijent uspesno izbrisan");
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", "Klijent nije obrisan");
        }

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public List<Klijent> getFiltriraniKlijenti() {
        return filtriraniKlijenti;
    }

    public void setFiltriraniKlijenti(List<Klijent> filtriraniKlijenti) {
        this.filtriraniKlijenti = filtriraniKlijenti;
    }

    public void vratiFilt() {
        System.out.println("Zovem filtriranje klijenata");
        System.out.println(filter);
        FacesMessage message;
        try {
            filtriraniKlijenti = klijentService.pronadjiKlijente(filter);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Poruka", "Sistem je pronasao"
                    + " klijente na osnovu zadate vrednosti");
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", "Sistem ne moze da nadje klijente na osnovu zadate vrednosti");
        }

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public boolean isIzmena() {
        return izmena;
    }

    public void setIzmena(boolean izmena) {
        this.izmena = izmena;
    }

    public void sacuvajIzmene(Klijent klijent, Radnik radnik) {
        FacesMessage message;
        try {
            for (Zaduzenje zaduzenje : klijent.getZaduzenjeList()) {
                if (zaduzenje.getDatumDO() != null && zaduzenje.getRazduzio() == null) {
                    zaduzenje.setRazduzio(radnik);
                }
            }
            klijentService.izmeniKlijenta(klijent);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Poruka", "Klijent uspesno izmenjen");

        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", "Sistem ne može da zapamti izmene klijenta");
            ex.printStackTrace();
        }

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
