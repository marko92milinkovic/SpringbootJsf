/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.dao.impl;

import projekat.dao.RadnikDAO;
import projekat.domen.Automobil;
import projekat.domen.Radnik;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author markom
 */
@Repository
@Transactional
public class RadnikDAOImpl implements RadnikDAO {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    @Override
    public Radnik vratiRadnika(String username, String password) throws Exception {
        Session openSession = getSession();

        Radnik radnik = (Radnik) openSession.createQuery("SELECT r FROM Radnik r where r.korisnickoIme=:username").
                setParameter("username", username).uniqueResult();
        if (radnik == null) {
            throw new Exception("wrong_username");
        }
        if (!radnik.getKorisnickaSifra().equals(password)) {
            throw new Exception("wrong_password");
        }

        return radnik;
    }

    @Override
    public Radnik pronadjiRadnika(String username) {
        Session openSession = getSession();

        Radnik radnik = (Radnik) openSession.createQuery("SELECT r FROM Radnik r where r.korisnickoIme=:username").
                setParameter("username", username).uniqueResult();
        if (radnik == null) {
            throw new RuntimeException("wrong_username");
        }
        return radnik;
    }

}
