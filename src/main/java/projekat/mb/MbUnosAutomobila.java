/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.mb;

import projekat.domen.Automobil;
import projekat.domen.Marka;
import projekat.domen.Model;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import projekat.services.AutomobilService;
import projekat.services.ModelService;

/**
 *
 * @author Marko
 */
//@ManagedBean
@Scope("view")
@Service
public class MbUnosAutomobila implements Serializable {

    private List<Marka> listaMarki;

    private List<Model> listaModela;

    private Automobil automobil;

    private Marka marka;

//    @EJB
//    SBModelLocal sbModel;
//    @EJB
//    SBAutomobilLocal sbAutomobil;
    @Autowired
    AutomobilService automobilService;

    @Autowired
    ModelService modelService;

    public MbUnosAutomobila() {
        automobil = new Automobil();
    }

    @PostConstruct
    private void init() {

        try {
            listaMarki = modelService.vratiSveMarke();
        } catch (Exception ex) {
            Logger.getLogger(MbUnosAutomobila.class.getName()).log(Level.ALL, ex.getMessage());
        }

    }

    public List<Marka> getListaMarki() {
        return listaMarki;
    }

    public void setListaMarki(List<Marka> listaMarki) {
        this.listaMarki = listaMarki;
    }

    public List<Model> getListaModela() {
        return listaModela;
    }

    public void setListaModela(List<Model> listaModela) {
        this.listaModela = listaModela;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    public void vratiModele() {
        if (marka == null) {
            return;
        }
        FacesMessage message;
        try {
            System.out.println("Vracam modele za marku: " + marka.getNaziv());
            listaModela = modelService.vratiModele(marka);

            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Poruka", "Sistem je pronasao modele na osnovu zadate marke");
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska",
                    "Modeli nisu pronadjeni");

        }

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

//    public void setModel(Model model) {
//        System.out.println("Pozivam setter modela");
//        this.automobil.setModel(model);
//    }
    public void sacuvaj() {

        FacesMessage message;
        try {
            automobilService.sacuvajAutomobil(automobil);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Poruka", "Automobil uspesno zapamcen");
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska", ex.getMessage());
        }

        RequestContext.getCurrentInstance().showMessageInDialog(message);

    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        System.out.println("Izabrana marka");
        if (marka == null) {
            automobil.setModel(null);
            listaModela.clear();
        }
        this.marka = marka;
    }

}
