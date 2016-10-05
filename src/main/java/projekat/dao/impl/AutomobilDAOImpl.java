/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.dao.impl;

import projekat.dao.AutomobilDAO;
import projekat.domen.Automobil;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AutomobilDAOImpl implements AutomobilDAO {

//    private SessionFactory sessionFactory;
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    @Override
    public void sacuvajAutomobil(Automobil automobil) throws Exception {

        Session session = getSession();

        //PREDUSLOV1
        if (automobil.getModel() == null || automobil.getBrojSasije().isEmpty() || automobil.getRegistarskiBroj().isEmpty()) {
            throw new Exception("Polja nisu popunjena");
        }

        //PEDUSLOV2
        List list = session.createQuery("SELECT a FROM Automobil a WHERE a.brojSasije=:brojSasije OR a.registarskiBroj=:registarskiBroj")
                .setParameter("brojSasije", automobil.getBrojSasije()).
                setParameter("registarskiBroj", automobil.getRegistarskiBroj())
                .list();

        if (list.size() > 0) {
            throw new Exception("Vec postoji automobil sa takvim podacima");
        }

        session.save(automobil);

    }

    @Override
    public List<Automobil> vratiSlobodneAutomobile() throws Exception {
        return getSession().
                createQuery("SELECT a from Automobil a where a NOT IN (SELECT z.automobil FROM Zaduzenje z where z.razduzio IS NULL)")
                .list();
    }

    @Override
    public Automobil vratiAutomobil(int id) {
        return (Automobil) getSession().get(Automobil.class, id);
    }

}
