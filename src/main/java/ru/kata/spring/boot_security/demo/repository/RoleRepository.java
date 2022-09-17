package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleRepository {
    Role getRoleById(Long id);

    Role getRoleByName(String name);
    List<Role> getAllRolesList();
}
