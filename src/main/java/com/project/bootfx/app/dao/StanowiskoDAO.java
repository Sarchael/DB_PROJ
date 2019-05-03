package com.project.bootfx.app.dao;

import com.project.bootfx.app.entity.Stanowisko;
import com.project.bootfx.app.entity.Wojewodztwo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StanowiskoDAO {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Stanowisko> getAll(){

        Session session = entityManager.unwrap(Session.class);
        Query<Stanowisko> query = session.createQuery("from Stanowisko", Stanowisko.class);

        return query.getResultList();
    }

    @Transactional
    public Stanowisko getByID(int id){

        Session session = entityManager.unwrap(Session.class);
        Stanowisko stanowisko = session.get(Stanowisko.class, id);

        return stanowisko;
    }

    @Transactional
    public void save(Stanowisko stanowisko){

        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(stanowisko);
    }

    @Transactional
    public boolean deleteByID(int id){

        Session session = entityManager.unwrap(Session.class);
        Stanowisko stanowisko = session.get(Stanowisko.class, id);
        if(stanowisko!=null) {
            //session.delete(employee);
            Query query = session.createQuery("delete from Stanowisko where id=:theid");
            query.setParameter("theid", id);
            query.executeUpdate();
            return true;
        }
        return false;
    }
}
