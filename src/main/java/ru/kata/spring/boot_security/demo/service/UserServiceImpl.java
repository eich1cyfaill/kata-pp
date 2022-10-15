package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.configs.PasswordEncoderHolder;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.*;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoderHolder passwordEncoderHolder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoderHolder passwordEncoderHolder) {
        this.userRepository = userRepository;
        this.passwordEncoderHolder = passwordEncoderHolder;
    }


    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }



    @Override
    @Transactional
    public void addUser(User user) {
        if (this.checkIfUserExists(user.getUsername())) {
            throw new RuntimeException(
                    "There is an account with that username:" + user.getUsername());
        }
        encodePassword(user);
        user.setEnabled(true);
        userRepository.saveAndFlush(user);
    }

    private void encodePassword(User user) {
        user.setPassword(passwordEncoderHolder.passwordEncoder().encode(user.getPassword()));
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        encodePassword(user);
        user.setEnabled(true);
        userRepository.saveAndFlush(user);
    }


    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findByUsername(username);
    }

    @Override
    public boolean checkIfUserExists(String username) {
        try {
            User user = userRepository.findByName(username);
            return user != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

}
