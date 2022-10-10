package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
//    Role getRoleById(Long id);
//
//    Role getRoleByName(String name);
//    List<Role> getAllRolesList();

    @Query("select r from Role r where r.name = :name")
    Role findByName(@Param("name") String name);
}
