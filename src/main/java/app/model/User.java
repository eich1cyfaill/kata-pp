package app.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

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

    public User() {}

    public User(String name, String lastName, int age, String country) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
