/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.konverteri;

import projekat.domen.Mesto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import projekat.services.MestoService;

/**
 *
 * @author student1
 */
//@FacesConverter(value = "mestoKNV")
@ManagedBean
@RequestScoped
@Component
public class MestoKonverter implements Converter {

//    @EJB
//    SBMestoLocal sbMesto;
    @Autowired
    @Qualifier("mestoServiceRestImpl")
    MestoService mestoService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !value.isEmpty()) {

            Integer ptt = Integer.parseInt(value);
            Mesto m = mestoService.vratiMesto(ptt);
            return m;

        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && (value instanceof Mesto)) {

            Mesto m = (Mesto) value;
            return m.getPttBroj().toString();

        }
        return "";
    }

}
