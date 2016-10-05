/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.dao.impl;

import projekat.dao.ZaduzenjeDAO;
import projekat.domen.Klijent;
import projekat.domen.Zaduzenje;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import validacija.Validacija;

@Repository
@Transactional
public class ZaduzenjeDAOImpl implements ZaduzenjeDAO {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    @Override
    public void sacuvajZaduzenja(List<Zaduzenje> zaduzenja) throws Exception {
        Session session = getSession();
        for (Zaduzenje z : zaduzenja) {

            Validacija.getInstance().validacijaZaduzenjaZaUnos(z);
        }

        for (Zaduzenje z : zaduzenja) {
            session.save(z);
        }
    }

    @Override
    public List<Zaduzenje> vratiZaduzenjaKlijenta(Klijent klijent) throws Exception {
        return getSession().
                createQuery("SELECT z FROM Zaduzenje z WHERE z.zaduzenjePK.klijent = :klijent").
                setParameter("klijent", klijent.getKlijentID()).list();
    }

    @Override
    public void izmeniZaduzenje(Zaduzenje zaduzenje) throws Exception {
        Validacija.getInstance().validacijaZaduzenjaZaIzmenu(zaduzenje);

        //  em.find(Zaduzenje.class, zaduzenje.getZaduzenjePK());
        getSession().saveOrUpdate(zaduzenje);
    }

    @Override
    public int getMaxValueOfRbr(Klijent klijent) throws Exception{
       return  (int) getSession().createQuery("SELECT MAX(z.zaduzenjePK.rBZaduzenja) FROM Zaduzenje z WHERE z.zaduzenjePK.klijent=:klijent")
                .setParameter("klijent", klijent.getKlijentID()).list().get(0);
    }
    

}
