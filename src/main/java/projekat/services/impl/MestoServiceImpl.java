/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services.impl;

import projekat.domen.Mesto;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.repository.MestoRepository;
import projekat.services.MestoService;

@Service("mestoServiceImpl")
@Transactional
public class MestoServiceImpl implements MestoService{

    @Autowired
    MestoRepository mestoRepository;
//    MestoDAO mestoDAO;
//    
//    @Override
//    public List<Mesto> vratiSvaMesta() throws Exception {
//        return mestoDAO.findAll();
//    }
//
//    @Override
//    public Mesto vratiMesto(int ptt) {
//        return mestoDAO.findOne(ptt);
//    }

    @Override
    public List<Mesto> vratiSvaMesta() throws Exception {
        return mestoRepository.findAll();
    }

    @Override
    public Mesto vratiMesto(int ptt) {
        return mestoRepository.findOne(ptt);
}

}
