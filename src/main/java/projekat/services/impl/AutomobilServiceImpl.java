/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services.impl;

import projekat.dao.AutomobilDAO;
import projekat.domen.Automobil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.services.AutomobilService;

@Service
public class AutomobilServiceImpl implements AutomobilService {

    @Autowired
    AutomobilDAO automobilDAO;
    
    @Override
    public void sacuvajAutomobil(Automobil automobil) throws Exception {
        automobilDAO.sacuvajAutomobil(automobil);
    }

    @Override
    public List<Automobil> vratiSlobodneAutomobile() throws Exception {
        return automobilDAO.vratiSlobodneAutomobile();
    }

    @Override
    public Automobil vratiAutomobill(int id) {
        return automobilDAO.vratiAutomobil(id);
    }
    
}
