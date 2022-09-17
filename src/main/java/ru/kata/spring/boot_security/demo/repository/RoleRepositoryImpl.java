package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getAllRolesList() {
        TypedQuery<Role> query = (TypedQuery<Role>) entityManager.createNativeQuery("select * from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = (TypedQuery<Role>) entityManager.createNativeQuery("select * from Role where name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
