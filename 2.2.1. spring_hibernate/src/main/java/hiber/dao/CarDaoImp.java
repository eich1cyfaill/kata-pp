package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

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

    public User getCarOwner(int series) {
        Session session = sessionFactory.openSession();
        try (session) {
            TypedQuery<Car> query = session.createQuery("from Car where series = :series");
            query.setParameter("series", series);
            return query.getSingleResult().getUser();
        }
    }

    public List<Car> getCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }
}
