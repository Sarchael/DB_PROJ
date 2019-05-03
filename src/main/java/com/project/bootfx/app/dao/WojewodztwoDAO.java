package com.project.bootfx.app.dao;

import com.project.bootfx.app.entity.Miasto;
import com.project.bootfx.app.entity.Wojewodztwo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class WojewodztwoDAO {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Wojewodztwo> getAll(){

        Session session = entityManager.unwrap(Session.class);
        Query<Wojewodztwo> query = session.createQuery("from Wojewodztwo", Wojewodztwo.class);

        return query.getResultList();
    }

    @Transactional
    public Wojewodztwo getByID(int id){

        Session session = entityManager.unwrap(Session.class);
        Wojewodztwo wojewodztwo = session.get(Wojewodztwo.class, id);

        return wojewodztwo;
    }

    @Transactional
    public void save(Wojewodztwo wojewodztwo){

        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(wojewodztwo);
    }

    @Transactional
    public boolean deleteByID(int id){

        Session session = entityManager.unwrap(Session.class);
        Wojewodztwo wojewodztwo = session.get(Wojewodztwo.class, id);
        if(wojewodztwo!=null) {
            //session.delete(employee);
            Query query = session.createQuery("delete from Wojewodztwo where id=:theid");
            query.setParameter("theid", id);
            query.executeUpdate();
            return true;
        }
        return false;
    }
}
