package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;


    public UserDaoImpl() {
    }

    @Override
    public List<User> getUserList() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }


    public void addRoleToUser(User user, Role role) {
        user.getAuthorities().add(role);
    }


    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getOneUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public boolean checkIfUserExists(String username) {
        try {
            TypedQuery<User> query = (TypedQuery<User>) entityManager.createNativeQuery("select * from User where username = :username", User.class);
            User user = query.setParameter("username", username).getSingleResult();
            return user != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = (TypedQuery<User>) entityManager.createNativeQuery("select * from User where username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
