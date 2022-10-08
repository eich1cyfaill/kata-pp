package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.configs.PasswordEncoderHolder;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserDao;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoderHolder passwordEncoderHolder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserDao adminDao, PasswordEncoderHolder passwordEncoderHolder, RoleService roleService) {
        this.userDao = adminDao;
        this.passwordEncoderHolder = passwordEncoderHolder;
        this.roleService = roleService;
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    @Transactional
    public void addUser(User user, List<Role> updatedRoles) {
        if (userDao.checkIfUserExists(user.getUsername())) {
            throw new RuntimeException(
                    "There is an account with that username:" + user.getUsername());
        }
        encodePassword(user);
        user.setRoles(updatedRoles);
        userDao.addUser(user);
    }

    private void encodePassword(User user) {
        user.setPassword(passwordEncoderHolder.passwordEncoder().encode(user.getPassword()));
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user, List<Role> roles) {
        encodePassword(user);
        user.setEnabled(true);
        user.setRoles(roles);
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public User getOneUser(Long id) {
        return userDao.getOneUser(id);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = this.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getAuthorities()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<? extends GrantedAuthority> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
    }
}
