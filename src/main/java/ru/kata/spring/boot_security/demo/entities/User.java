package ru.kata.spring.boot_security.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
@Data
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

    @ManyToMany(fetch = FetchType.LAZY)
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




    public User(Long id, String name, String lastName, int age, Set<Role> roles, String username, String password, boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    public User (String username, String password, String name, String lastName, int age, Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.roles = roles;
        this.username = username;
        this.password = password;
    }

    public User() {}

    public User(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(getClass() != o.getClass() || o == null) return true;
        return id.equals(((User) o).id)
                && name.equals(((User) o).name)
                && lastName.equals(((User) o).lastName)
                && age == ((User) o).age;
    }

    @Override
    public int hashCode() {
        int result = 29;
        result = (name.hashCode() * lastName.hashCode() * (int)(id - (id >>> 32))) * result;
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

    public void addRole(Role role) {
        this.roles.add(role);
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

    public Set<Role> getRoles() { return roles; }

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
