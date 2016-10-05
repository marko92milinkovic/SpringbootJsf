/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services;

import projekat.domen.Radnik;

/**
 *
 * @author markom
 */
public interface RadnikService {

   public Radnik vratiRadnika(String username, String password) throws Exception;

    public Radnik pronadjiRadnika(String username);

}
