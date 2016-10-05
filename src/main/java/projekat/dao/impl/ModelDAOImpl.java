/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.dao.impl;

import projekat.dao.ModelDAO;
import projekat.domen.Marka;
import projekat.domen.Model;
import projekat.domen.ModelPK;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ModelDAOImpl implements ModelDAO {

     @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

    @Override
    public List<Marka> vratiSveMarke() throws Exception {
        return getSession().createQuery("from Marka").list();
    }

    @Override
    public List<Model> vratiModele(Marka marka) throws Exception {
        return getSession().createQuery("SELECT m FROM Model m WHERE m.modelPK.marka = :marka").
                setParameter("marka", marka.getMarkaID()).list();
    }

    @Override
    public Marka vratiMarku(int markaID) {
        return (Marka) getSession().get(Marka.class, markaID);
    }

    @Override
    public Model vratiModel(int modelID, int markaID) {
        return (Model) getSession().get(Model.class, new ModelPK(markaID, modelID));

    }

}
