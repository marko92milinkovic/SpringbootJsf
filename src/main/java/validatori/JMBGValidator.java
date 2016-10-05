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
 * @author student1
 */
@FacesValidator(value = "jmbgVal")
public class JMBGValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value != null) {

            String jmbg = value.toString();

            if (jmbg.length() != 13) {

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "JMBG mora imati 13 cifara"));

            }

            for (int i = 0; i < jmbg.length(); i++) {
                if (!Character.isDigit(jmbg.charAt(i))) {

                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "JMBG moze imati samo cifre"));

                }
            }
            
        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "JMBG je obavezan"));

        }

    }

}
