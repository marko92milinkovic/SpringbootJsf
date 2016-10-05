/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services.impl;

import projekat.dao.ModelDAO;
import projekat.domen.Marka;
import projekat.domen.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.services.ModelService;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelDAO modelDAO;
    
    @Override
    public List<Marka> vratiSveMarke() throws Exception {
        return modelDAO.vratiSveMarke();
    }

    @Override
    public List<Model> vratiModele(Marka marka) throws Exception {
        return modelDAO.vratiModele(marka);
    }

    @Override
    public Marka vratiMarku(int markaID) {
        return modelDAO.vratiMarku(markaID);
    }

    @Override
    public Model vratiModel(int modelID, int markaID) {
        return modelDAO.vratiModel(modelID, markaID);
    }
    
}
