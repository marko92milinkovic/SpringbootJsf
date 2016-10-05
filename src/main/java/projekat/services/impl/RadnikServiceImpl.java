/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services.impl;

import projekat.dao.RadnikDAO;
import projekat.domen.Radnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.services.RadnikService;

/**
 *
 * @author markom
 */
@Service
public class RadnikServiceImpl implements RadnikService {

    @Autowired
    RadnikDAO radnikDAO;

    @Override
    public Radnik vratiRadnika(String username, String password) throws Exception {
        return radnikDAO.vratiRadnika(username, password);
    }

    @Override
    public Radnik pronadjiRadnika(String username) {
        return radnikDAO.pronadjiRadnika(username);
    }

}
