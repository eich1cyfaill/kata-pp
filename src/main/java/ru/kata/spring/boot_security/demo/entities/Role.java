package ru.kata.spring.boot_security.demo.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Column
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Role(String name) {
        this.name = name;
    }

    public Role() {

    }


    @Override
    public String getAuthority() {
        return "ROLE_" + this.getName();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
