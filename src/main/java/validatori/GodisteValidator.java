/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatori;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Marko
 */
@FacesValidator(value = "godisteVal")
public class GodisteValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

       
        
        if (value == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste uneli godiste", ""));
        }

        try {
            if (Integer.parseInt(value.toString())<1) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        
                        "Godiste mora biti pozitivan ceo broj", "nije ceo poz br"));

            }
            
        } catch (NumberFormatException e) {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neocekivana greska", "gdb"));

        } catch (Exception e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neocekivana greska", "gdb"));

        }

    }

}
