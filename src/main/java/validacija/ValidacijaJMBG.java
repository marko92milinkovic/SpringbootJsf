/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacija;

/**
 *
 * @author Marko
 */
public class ValidacijaJMBG {

    public static void proveriJMBG(String jmbg) throws ValidacijaKlijentaException {

        char[] niz = jmbg.toCharArray();

        if(niz.length!=13) {
            throw new ValidacijaKlijentaException("Jmbg sadrzi tacno 13 cifara");
            
        }
        
        for (char c : niz) {
            if (!Character.isDigit(c)) {
                throw new ValidacijaKlijentaException("Jmbg sadrzi samo cifre");
            }
        }

    }

}
