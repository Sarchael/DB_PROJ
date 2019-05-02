package com.project.bootfx.app.dao;

import com.project.bootfx.app.entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PracownikDAO {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Pracownik> getAll(){

        Session session = entityManager.unwrap(Session.class);
        Query<Pracownik> query = session.createQuery("from Pracownik", Pracownik.class);

        return query.getResultList();
    }

    @Transactional
    public Pracownik getByID(int id){

        Session session = entityManager.unwrap(Session.class);
        Pracownik pracownik = session.get(Pracownik.class, id);

        return pracownik;
    }

    @Transactional
    public void save(Pracownik pracownik){

        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(pracownik);
    }

    @Transactional
    public boolean deleteByID(int id){

        Session session = entityManager.unwrap(Session.class);
        Pracownik pracownik = session.get(Pracownik.class, id);
        if(pracownik!=null) {
            //session.delete(employee);
            Query query = session.createQuery("delete from Pracownik where id=:theid");
            query.setParameter("theid", id);
            query.executeUpdate();
            return true;
        }
        return false;
    }
}
