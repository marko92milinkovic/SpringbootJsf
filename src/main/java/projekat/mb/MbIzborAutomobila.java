/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.mb;

import projekat.domen.Automobil;
import projekat.domen.Marka;
import projekat.domen.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import projekat.services.AutomobilService;

/**
 *
 * @author Marko
 */
@ManagedBean(name = "mbIzborAutomobila")
//@ManagedBean
//@ViewScoped
//@Service
@Scope("view")
@Service
public class MbIzborAutomobila {

    private List<Automobil> listaAutomobila;

    private List<Automobil> filtriraniAutomobili;

    private List<Marka> listaMarki;

    private List<Model> listaModela;

    private Automobil automobil;

    private Marka marka;

    //  private boolean omoguci = false;
//    @EJB
//    SBAutomobilLocal sbAutomobil;
    @Autowired
//    @ManagedProperty(value="#{automobilService}")
    AutomobilService automobilService;

    public MbIzborAutomobila() {

    }

    @PostConstruct
    public void init() {
        
        System.out.println("Pozvao se init mbIzbor");

        listaMarki = new ArrayList<>();
        listaModela = new ArrayList<>();
        automobil = new Automobil();
        filtriraniAutomobili = new ArrayList<>();
        listaAutomobila = new ArrayList<>();

        try {
            listaAutomobila = automobilService.vratiSlobodneAutomobile();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(MbIzborAutomobila.class.getName()).log(Level.SEVERE, null, ex);
        }

        listaAutomobila.stream().map((a) -> a.
                getModel().
                getMarka1()).filter((ma) -> (!listaMarki.contains(ma))).forEach((ma) -> {
            listaMarki.add(ma);
        });

        listaAutomobila.stream().filter((a) -> (!listaModela.contains(a.getModel()))).map((a) -> {
            listaModela.add(a.getModel());
            return a;
        }).forEach((a) -> {
            System.out.println(a.getModel());
        });

    }
    
    public List<Automobil> vratiAutomobileIzBaze() {
         try {
            listaAutomobila = automobilService.vratiSlobodneAutomobile();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(MbIzborAutomobila.class.getName()).log(Level.SEVERE, null, ex);
        }

        listaAutomobila.stream().map((a) -> a.
                getModel().
                getMarka1()).filter((ma) -> (!listaMarki.contains(ma))).forEach((ma) -> {
            listaMarki.add(ma);
        });

        listaAutomobila.stream().filter((a) -> (!listaModela.contains(a.getModel()))).map((a) -> {
            listaModela.add(a.getModel());
            return a;
        }).forEach((a) -> {
            System.out.println(a.getModel());
        });
        
        
        
        return listaAutomobila;
    }
    

    public List<Automobil> getListaAutomobila() {
        return listaAutomobila;
    }

    public void setListaAutomobila(List<Automobil> listaAutomobila) {
        this.listaAutomobila = listaAutomobila;
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

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public void izmeniModele() {

        listaModela = new ArrayList<>();
        for (Automobil a : filtriraniAutomobili) {
            if (a.getModel().getMarka1().equals(marka) && !listaModela.contains(a.getModel())) {
                listaModela.add(a.getModel());
            }
        }
        //     omoguci = true;
    }

    public List<Automobil> getFiltriraniAutomobili() {
        return filtriraniAutomobili;
    }

    public void setFiltriraniAutomobili(List<Automobil> filtriraniAutomobili) {
        this.filtriraniAutomobili = filtriraniAutomobili;
    }

}
