package com.project.bootfx.app.dao;

import com.project.bootfx.app.entity.Miasto;
import com.project.bootfx.app.entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MiastoDAO {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Miasto> getAll(){

        Session session = entityManager.unwrap(Session.class);
        Query<Miasto> query = session.createQuery("from Miasto", Miasto.class);

        return query.getResultList();
    }

    @Transactional
    public Miasto getByID(int id){

        Session session = entityManager.unwrap(Session.class);
        Miasto miasto = session.get(Miasto.class, id);

        return miasto;
    }

    @Transactional
    public void save(Miasto miasto){

        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(miasto);
    }

    @Transactional
    public boolean deleteByID(int id){

        Session session = entityManager.unwrap(Session.class);
        Miasto miasto = session.get(Miasto.class, id);
        if(miasto!=null) {
            //session.delete(employee);
            Query query = session.createQuery("delete from Miasto where id=:theid");
            query.setParameter("theid", id);
            query.executeUpdate();
            return true;
        }
        return false;
    }
}
