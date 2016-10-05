/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services;

import projekat.domen.Marka;
import projekat.domen.Model;
import java.util.List;

/**
 *
 * @author markom
 */
public interface ModelService {

    List<Marka> vratiSveMarke() throws Exception;
    // List<Model> vratiSveModele() throws Exception;

    List<Model> vratiModele(Marka marka) throws Exception;

    Marka vratiMarku(int markaID);

    Model vratiModel(int modelID, int markaID);

}
