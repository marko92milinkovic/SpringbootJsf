/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacija;

import projekat.domen.Automobil;
import projekat.domen.Klijent;
import projekat.domen.Zaduzenje;

/**
 *
 * @author Marko
 */
public class Validacija {

    private Validacija() {
    }

    public static Validacija getInstance() {
        return ValidacijaHolder.INSTANCE;
    }

    private static class ValidacijaHolder {

        private static final Validacija INSTANCE = new Validacija();
    }

    public void validacijaKlijenta(Klijent klijent) throws Exception {

        if (klijent == null) {
            throw new Exception("Klijent nije inicijalizovan!");
        }
        
        if(klijent.getIme()==null || "".equals(klijent.getIme()) || klijent.getPrezime()==null || 
                "".equals(klijent.getPrezime()) || klijent.getJmbg()==null || "".equals(klijent.getJmbg()) ) {
            throw new Exception("Niste popunili obavezna polja klijenta");
        }
        
        ValidacijaJMBG.proveriJMBG(klijent.getJmbg());

        

    }

    public void validacijaZaduzenjaZaIzmenu(Zaduzenje zaduzenje) throws Exception {

        if (zaduzenje == null) {
            throw new Exception("Zaduzenje nije inicijalizovano!");
        }
        
        if(zaduzenje.getKlijent1()==null) {
            throw new Exception("Niste uneli klijenta za dato razduzenje");
        }
        
        if(zaduzenje.getAutomobil()==null) {
            throw new Exception("Niste izabrali automobil");
        }
        
        if (zaduzenje.getDatumDO()!=null && zaduzenje.getRazduzio()==null) {
            throw new Exception("Niste izabrali radnika koji je razduzio");
        }
        
        if(zaduzenje.getRazduzio()!=null && zaduzenje.getDatumDO()==null) {
            throw new Exception("Niste uneli datum razduzenja");
        }
        
        if(zaduzenje.getDatumDO()!=null && zaduzenje.getDatumDO().before(zaduzenje.getDatumOD())) {
            throw new Exception("Datum do mora biti posle datuma od");
        }
        
    }

    public void validacijaZaduzenjaZaUnos(Zaduzenje zaduzenje) throws Exception {
        
if (zaduzenje == null) {
            throw new Exception("Zaduzenje nije inicijalizovano!");
        }

        if(zaduzenje.getAutomobil()==null) {
            throw new Exception("Niste izabrali automobil");
        }
        
        if (zaduzenje.getZaduzio()==null) {
            throw new Exception("Niste izabrali radnika koji je zaduzio");
        }
        
        if(zaduzenje.getDatumOD()==null) {
            throw new Exception("Niste uneli datum zaduzenja");
        }
    }

    public void validacijaAutomobila(Automobil a) throws Exception {
        if (a == null) {
            throw new Exception("Automobil nije inicijalizovan!");
        }

        if (a.getBrojSasije()==null || "".equals(a.getBrojSasije())) {
            throw new Exception("Niste uneli broj sasije ");
        }
        
        if(a.getRegistarskiBroj()==null || "".equals(a.getRegistarskiBroj())){
            throw new Exception("Niste uneli registarsi broj automobila");
        }
        
        
//        if (Pattern.matches("[A-Z]{2}-\\d{3}[A-Z]{2}", a.getRegistarskiBroj())) {
//            throw new ValidacijaAutomobilaException("Niste dobro uneli registarski broj");
//        }
    }
}
