/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.dao;

import projekat.domen.Klijent;
import projekat.domen.Zaduzenje;
import java.util.List;

/**
 *
 * @author marko
 */
public interface ZaduzenjeDAO {

    void sacuvajZaduzenja(List<Zaduzenje> zaduzenja) throws Exception;

    List<Zaduzenje> vratiZaduzenjaKlijenta(Klijent klijent) throws Exception;

    void izmeniZaduzenje(Zaduzenje zaduzenje) throws Exception;
    
    int getMaxValueOfRbr(Klijent klijent) throws Exception;
}
