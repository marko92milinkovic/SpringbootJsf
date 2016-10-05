/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services;

import projekat.domen.Mesto;
import java.util.List;

/**
 *
 * @author markom
 */
public interface MestoService {

    List<Mesto> vratiSvaMesta() throws Exception;

    Mesto vratiMesto(int ptt);

}
