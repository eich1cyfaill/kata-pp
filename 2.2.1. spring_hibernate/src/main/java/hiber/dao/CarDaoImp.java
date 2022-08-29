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
public class CarDaoImp {
    @Autowired
    private SessionFactory sessionFactory;

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
            TypedQuery<Car> query = session.createQuery("from Car where series = :series and model = :model");
            query.setParameter("series", series);
            query.setParameter("model", model);
            User user = query.getSingleResult().getUser();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }
}
