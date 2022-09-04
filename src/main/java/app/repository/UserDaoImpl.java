package app.repository;


import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;


    @Autowired
    public UserDaoImpl() {
    }

    @Override
    public List<User> getUserList() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User userInDatabase = entityManager.find(User.class, user.getId());
        userInDatabase.setName(user.getName());
        userInDatabase.setLastName(user.getLastName());
        userInDatabase.setAge(user.getAge());
        userInDatabase.setCountry(user.getCountry());
        entityManager.persist(userInDatabase);
    }

    @Override
    @Transactional
    public User getOneUser(Long id) {
        return entityManager.find(User.class, id);
    }
}
