package ru.kata.spring.boot_security.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    @Override
    public String getAuthority() {
        return "ROLE_" + this.getName();
    }

    @Override
    public String toString() {
        return name;
    }
}
