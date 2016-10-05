/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services.impl;

import projekat.dao.ZaduzenjeDAO;
import projekat.domen.Klijent;
import projekat.domen.Zaduzenje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.services.ZaduzenjeService;

@Service
public class ZaduzenjeServiceImpl implements ZaduzenjeService {

    @Autowired
    ZaduzenjeDAO zaduzenjeDAO;
    
    
    @Override
    public void sacuvajZaduzenja(List<Zaduzenje> zaduzenja) throws Exception {
        zaduzenjeDAO.sacuvajZaduzenja(zaduzenja);
    }

    @Override
    public List<Zaduzenje> vratiZaduzenjaKlijenta(Klijent klijent) throws Exception {
        return zaduzenjeDAO.vratiZaduzenjaKlijenta(klijent);
    }

    @Override
    public void izmeniZaduzenje(Zaduzenje zaduzenje) throws Exception {
        zaduzenjeDAO.izmeniZaduzenje(zaduzenje);
    }

    @Override
    public int getMaxValueOfRbr(Klijent klijent) throws Exception {
        return zaduzenjeDAO.getMaxValueOfRbr(klijent);
    }
    
    
}
