/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import projekat.domen.Automobil;
import projekat.domen.Klijent;
import projekat.domen.Mesto;
import projekat.domen.Radnik;
import projekat.services.AutomobilService;
import projekat.services.KlijentService;
import projekat.services.MestoService;
import projekat.services.RadnikService;

/**
 *
 * @author marko
 */
@RestController
public class BasicController {

    @Autowired
    AutomobilService automobilService;
    
    @Autowired
    RadnikService radnikService;
   
    @Autowired
    @Qualifier("mestoServiceImpl")
    MestoService mestoService;
    
    @Autowired
    @Qualifier("klijentServiceImpl")
            
    KlijentService klijentService;
    
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "meters/notfound";
    }

    @RequestMapping(value = "/rest/cars", produces = "application/json")
    public List<Automobil> getAllAvailableCars() throws Exception {
        return automobilService.vratiSlobodneAutomobile();
    }

    @RequestMapping(value = "/rest/cars/{id}", produces = "application/json")
    Automobil getAutomobil(@PathVariable Integer id) {
        return automobilService.vratiAutomobill(id);
    }
    
    @RequestMapping(value = "/rest/employees/{username}", produces = "application/json")
    Radnik getRadnik(@PathVariable String username) {
        Radnik radnik =  radnikService.pronadjiRadnika(username);
        System.out.println(radnik);
        return radnik;
    }
    
    @RequestMapping(value = "/rest/places", produces = "application/json")
    List<Mesto> getAllMesto() throws Exception{
        return mestoService.vratiSvaMesta();
    }
    
    @RequestMapping(value="/rest/client", consumes = "application/json")
    public void saveClient(Klijent klijent) throws Exception{
        klijentService.sacuvaj(klijent);
    }
}
