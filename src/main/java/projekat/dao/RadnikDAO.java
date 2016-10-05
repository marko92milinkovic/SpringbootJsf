package projekat.dao;


import projekat.domen.Radnik;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author markom
 */
public interface RadnikDAO {
    public abstract Radnik vratiRadnika(String username, String password) throws Exception;

    public Radnik pronadjiRadnika(String username);
}
