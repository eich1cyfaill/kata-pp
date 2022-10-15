package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.name = :name")
    Role findByName(@Param("name") String name);
}
