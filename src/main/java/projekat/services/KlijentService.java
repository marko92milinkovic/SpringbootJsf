/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services;

import projekat.domen.Klijent;
import java.util.List;

/**
 *
 * @author markom
 */
public interface KlijentService {

    void sacuvaj(Klijent klijent) throws Exception;

    List<Klijent> pronadjiKlijente(String kljuc) throws Exception;

    void obrisi(Klijent klijent) throws Exception;

    void izmeniKlijenta(Klijent klijent) throws Exception;

}
