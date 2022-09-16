package ru.kata.spring.boot_security.demo.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "country")
    private String country;



    public User(Long id, String name, String lastName, int age, String country, Set<Role> roles, String username, String password, boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column
    private String username;

    @Column
    private String password;

    @Column(name = "is_enabled")
    private boolean isEnabled;




    public User() {}

    @Autowired
    public User(String name, String lastName, int age, String country) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(getClass() != o.getClass() || o == null) return true;
        return id.equals(((User) o).id)
                && name.equals(((User) o).name)
                && lastName.equals(((User) o).lastName)
                && age == ((User) o).age
                && country.equals(((User) o).country);
    }

    @Override
    public int hashCode() {
        int result = 29;
        result = (name.hashCode() * lastName.hashCode() * country.hashCode() * (int)(id - (id >>> 32))) * result;
        return 29 * result + age;
    }

    @Override
    public Collection<Role> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
