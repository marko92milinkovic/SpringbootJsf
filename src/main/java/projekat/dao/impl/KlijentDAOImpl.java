/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.dao.impl;

import projekat.dao.KlijentDAO;
import projekat.domen.Klijent;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projekat.domen.Zaduzenje;
import validacija.Validacija;

@Repository
@Transactional
public class KlijentDAOImpl implements KlijentDAO {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    @Override
    public void sacuvaj(Klijent klijent) throws Exception {
        List resultList = getSession().createQuery("SELECT k from Klijent k WHERE k.jmbg= :jmbg").
                setParameter("jmbg", klijent.getJmbg()).list();

        if (!resultList.isEmpty()) {
            throw new Exception("Vec postoji klijent sa unetim podacima (jmbg vec postoji)");
        }
        getSession().save(klijent);

    }

    @Override
    public List<Klijent> pronadjiKlijente(String kljuc) throws Exception {
        return getSession().createQuery("SELECT k FROM Klijent k WHERE LOCATE(:kljuc,k.ime)>0 OR LOCATE(:kljuc,k.prezime)>0").
                setParameter("kljuc", kljuc).list();

    }

    @Override
    public void obrisi(Klijent klijent) throws Exception {
        //proveri da li radi, mozda treba da bude managed
        getSession().delete(klijent);
    }

    @Override
    public void izmeniKlijenta(Klijent klijent) throws Exception {
        if (klijent.getIme().isEmpty() || klijent.getPrezime().isEmpty() || klijent.getJmbg().isEmpty()) {
            throw new Exception("Polja nisu popunjena .. EJB");
        }
        
        Validacija.getInstance().validacijaKlijenta(klijent);
        
        for (Zaduzenje zaduzenje : klijent.getZaduzenjeList()) {
             Validacija.getInstance().validacijaZaduzenjaZaIzmenu(zaduzenje);
        }
        
        getSession().saveOrUpdate(klijent);
    }

}
