package DBControll;

import Entities.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DBControll {

    private SessionFactory sfactory;
    private Session session;

    public DBControll(){
        sfactory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pracownik.class) // tu trzeba bedzie dodac inne encje jak się je doda
                .buildSessionFactory();
    }

    public <T> void create(T c){

        session = sfactory.getCurrentSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
    }

    public <T> T singleReadByID(Class<T> c, int id) throws Exception{

        session = sfactory.getCurrentSession();
        session.beginTransaction();

        T t = session.get(c, id);

        session.getTransaction().commit();

        return t;
    }

    public <T> List<T> readAll(Class<T> c){

        session = sfactory.getCurrentSession();
        session.beginTransaction();
        List<T> list = session.createQuery("from " + c.getSimpleName()).getResultList();
        session.getTransaction().commit();
        return list;
    }
}
