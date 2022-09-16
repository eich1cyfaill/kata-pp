package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entities.Role;

public interface RoleRepository {
    Role getRoleById(Long id);
}
