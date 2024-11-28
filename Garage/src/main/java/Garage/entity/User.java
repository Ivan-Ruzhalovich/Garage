package Garage.entity;

import Garage.entity.tech.TechCar;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "Date_of_birth")
    private String dateOfBirth;
    @Column(name = "City")
    private String city;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name_auth")
    private SecurityUsers userNameAuth;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId",
            fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Car> cars;

    public User() {
    }

    public User( String name, String surname, String dateOfBirth,
                String city, String phoneNumber, SecurityUsers userNameAuth, List<Car> cars) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.userNameAuth = userNameAuth;
        this.cars = cars;
    }


    public List<Car> getCars() {
        return cars;
    }


    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        System.out.println(this.dateOfBirth);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String salary) {
        this.city = salary;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void addCarToUser(Car car){
        if (cars == null){
            cars = new ArrayList<>();
        }
        cars.add(car);
    }

    public SecurityUsers getUserNameAuth() {
        return userNameAuth;
    }

    public void setUserNameAuth(SecurityUsers userNameAuth) {
        this.userNameAuth = userNameAuth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userNameAuth=" + userNameAuth +
                ", cars=" + cars +
                '}';
    }
}
