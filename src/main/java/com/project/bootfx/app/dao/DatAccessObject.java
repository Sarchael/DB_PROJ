package com.project.bootfx.app.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DatAccessObject implements IDataAccessObject {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public <T> void save(T c) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(c);
    }

    @Override
    @Transactional
    public <T> T getById(Class<T> c, int id) {
        Session session = entityManager.unwrap(Session.class);
        T entity = session.get(c, id);

        return entity;
    }

    @Override
    @Transactional
    public <T> List<T> readAll(Class<T> c) {
        Session session = entityManager.unwrap(Session.class);
        Query<T> query = session.createQuery("from " + c.getSimpleName(), c);

        return query.getResultList();
    }

    @Override
    @Transactional
    public <T> boolean deletebyID(Class<T> c, int id) {
        Session session = entityManager.unwrap(Session.class);
        T entity = session.get(c, id);
        if (entity != null) {
            //session.delete(employee);
            Query query = session.createQuery("delete from" + c.getSimpleName() + "where id=:theid");
            query.setParameter("theid", id);
            query.executeUpdate();
            return true;
        }
        return false;
    }
}
