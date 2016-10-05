/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.dao;

import projekat.domen.Mesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marko
 */
@Repository
public interface MestoDAO extends JpaRepository<Mesto, Integer>{

//    List<Mesto> vratiSvaMesta() throws Exception;
//
//    Mesto vratiMesto(int ptt);
}
