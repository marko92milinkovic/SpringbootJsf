/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services.impl;

import projekat.dao.KlijentDAO;
import projekat.domen.Klijent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.services.KlijentService;

@Service("klijentServiceImpl")
public class KlijentServiceImpl implements KlijentService {

    @Autowired
    KlijentDAO klijentDAO;
    
    @Override
    public void sacuvaj(Klijent klijent) throws Exception {
        klijentDAO.sacuvaj(klijent);
    }

    @Override
    public List<Klijent> pronadjiKlijente(String kljuc) throws Exception {
        return klijentDAO.pronadjiKlijente(kljuc);
    }

    @Override
    public void obrisi(Klijent klijent) throws Exception {
        klijentDAO.obrisi(klijent);
    }

    @Override
    public void izmeniKlijenta(Klijent klijent) throws Exception {
        klijentDAO.izmeniKlijenta(klijent);
    }
    
}
