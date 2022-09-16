package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleRepositoryImpl implements RoleRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
