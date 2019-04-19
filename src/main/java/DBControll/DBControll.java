package DBControll;

import Entities.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBControll {

    private SessionFactory sfactory;
    private Session session;

    public DBControll(){
        sfactory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pracownik.class)
                .buildSessionFactory();
    }

    public void create(String name, String surname, String city){

        Pracownik p = new Pracownik(name, surname, city);

        session = sfactory.getCurrentSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
    }

    public Pracownik singleRead(int id) throws Exception{

        session = sfactory.getCurrentSession();
        session.beginTransaction();

        Pracownik p = session.get(Pracownik.class, id);

        session.getTransaction().commit();

        return p;
    }
}
