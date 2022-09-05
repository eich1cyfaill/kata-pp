package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
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
