/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.konverteri;

import projekat.domen.Marka;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projekat.services.ModelService;

/**
 *
 * @author Marko
 */
//@FacesConverter(value = "markaKNV")
//@ManagedBean
@RequestScoped
@Component
public class MarkaKonverter implements Converter {

//    @EJB
//    SBModelLocal sbModel;
    @Autowired
    ModelService modelService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                int id = Integer.parseInt(value);
                return modelService.vratiMarku(id);
            } catch (Exception e) {

            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null && (value instanceof Marka)) {

            Marka m = (Marka) value;

            return m.
                    getMarkaID().
                    toString();

        }
        return null;

    }

}
