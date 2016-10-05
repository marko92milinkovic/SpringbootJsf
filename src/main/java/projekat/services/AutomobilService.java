/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services;

import projekat.domen.Automobil;
import java.util.List;

/**
 *
 * @author marko
 */
public interface AutomobilService {
    void sacuvajAutomobil(Automobil automobil) throws Exception;
    
    List<Automobil> vratiSlobodneAutomobile() throws Exception;

    public Automobil vratiAutomobill(int id);
}
