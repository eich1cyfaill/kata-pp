package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class CarDaoImp implements CarDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Car car) {
        Session session = sessionFactory.openSession();
        try (session) {
            session.beginTransaction();
            session.save(car);
            session.getTransaction().commit();
        }
    }

    public User getCarOwner(int series, String model) {
        Session session = sessionFactory.openSession();
        try (session) {
            TypedQuery<User> query = session.createQuery("from User where car.series = :car and car.model = :model");
            query.setParameter("car", series);
            query.setParameter("model", model);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
