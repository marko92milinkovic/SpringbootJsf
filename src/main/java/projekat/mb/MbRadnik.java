/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.mb;

import projekat.domen.Radnik;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projekat.ClientLog;
import projekat.services.RadnikService;

/**
 *
 * @author Marko
 */
//@ManagedBean
@SessionScoped
@Component
public class MbRadnik implements Serializable {

    private Radnik radnik;

//    @EJB
//    SBRadnikLocal sbRadnik;
    @Autowired
    RadnikService radnikService;

    ClientLog log = new ClientLog(getClass());

    public MbRadnik() {

    }

    @PostConstruct
    private void init() {
        log.debug("*** Init mbRadnik");
        radnik = new Radnik();
        ulogovan = false;

    }

    public String ulogujSe() {

        FacesMessage message;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle
                = facesContext.getApplication().getResourceBundle(
                        facesContext, "msg");
        try {
            radnik = radnikService.vratiRadnika(radnik.getKorisnickoIme(), radnik.getKorisnickaSifra());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("message"),
                    bundle.getString("welcome_message") + ": "
                    + radnik.getIme() + " " + radnik.getPrezime());
//
//            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//
//            session.setAttribute("radnik", radnik);

//            RequestContext.getCurrentInstance().showMessageInDialog(message);
            ulogovan = true;

        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("error"),
                    bundle.getString(ex.getMessage()));
            ulogovan = false;

        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        return ulogovan?"index.xhtml?faces-redirect=true":"#";
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    //Dodato
    private boolean ulogovan;

    public boolean isUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(boolean ulogovan) {
        this.ulogovan = ulogovan;
    }

    public String izlogujSe() {
        setRadnik(new Radnik());
        ulogovan = false;
        return "appLogout";
    }

}
