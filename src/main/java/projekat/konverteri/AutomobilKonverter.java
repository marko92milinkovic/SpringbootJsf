/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.konverteri;

import projekat.domen.Automobil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.stereotype.Component;
import projekat.services.AutomobilService;

/**
 *
 * @author Marko
 */
//@FacesConverter(value = "automobilKNV")
@ManagedBean
@RequestScoped
@Component
public class AutomobilKonverter implements Converter {

//    @EJB
//    SBAutomobilLocal sbAutomobil;
    
//    @Autowired
    AutomobilService automobilService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                int id = Integer.parseInt(value);
                
                Automobil a = automobilService.vratiAutomobill(id);
                System.out.println(a.getRegistarskiBroj());
                return a;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && (value instanceof Automobil)) {

            Automobil a = (Automobil) value;
            

            return a.
                    getAutomobilID().
                    toString();
        }
        return null;
    }

}
