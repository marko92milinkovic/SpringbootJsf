/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.konverteri;

import projekat.domen.Model;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projekat.services.ModelService;

//@FacesConverter(value = "modelKNV")
@ManagedBean
@RequestScoped
@Component
public class ModelKonverter implements Converter, Serializable {

//    @EJB
//    SBModelLocal sbModel;
    @Autowired
    ModelService modelService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                String[] split = value.split(" ");
                Model model = modelService.vratiModel(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                return model;
            } catch (Exception e) {

            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null && (value instanceof Model)) {
            Model model = (Model) value;

            return model.
                    getModelPK().
                    toString();

        }
        return "";
    }
}
