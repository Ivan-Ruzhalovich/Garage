package Garage.entity;

import Garage.Classes.CarInfo;
import Garage.entity.tech.Refuel;
import Garage.entity.tech.TechCar;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "car")
public class Car {

    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "Model")
    private String model;

    @Column(name = "Year")
    private int year;

    @Column(name = "Kilometrage")
    private int kilometrage;

    @Column(name = "Engine")
    private String engine;

    @Column(name = "Transmission")
    private String transmission;

    @Column(name = "User_id")
    private long userId;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, targetEntity = TechCar.class, mappedBy = "carId")
    private List<TechCar> techs;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = Refuel.class, cascade = CascadeType.ALL,
            mappedBy = "carId")
    private List<Refuel> refuels;


    public Car() {
    }

    public Car(long userId) {
        this.userId = userId;
    }

    public Car(String brand, String model, int year, int kilometrage, String engine,
               String transmission, long userId, List<TechCar> techs, List<Refuel> refuels) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometrage = kilometrage;
        this.engine = engine;
        this.transmission = transmission;
        this.userId = userId;
        this.techs = techs;
        this.refuels = refuels;
    }

    public List<TechCar> getTechs() {
        return techs;
    }

    public void setTechs(List<TechCar> techs) {
        this.techs = techs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Refuel> getRefuels() {
        return refuels;
    }

    public void setRefuels(List<Refuel> refuels) {
        this.refuels = refuels;
    }

    public void addTechToListTech(TechCar techCar) {
        techs.add(techCar);
    }

    public void addRefuelToListRefuel(Refuel refuel) {
        refuels.add(refuel);
    }

    public void setAcualityKilometrage() {
        if (!this.getTechs().isEmpty() && !this.getRefuels().isEmpty()) {
            this.setKilometrage(Math.max(
                    Collections.max(this.getRefuels().stream().map(e -> e.getKilometrage())
                            .collect(Collectors.toList())),
                    Collections.max(this.getTechs().stream().map(e -> e.getKilometrage())
                            .collect(Collectors.toList()))));
        } else if (this.getTechs().isEmpty()&&!this.getRefuels().isEmpty()) {
            this.setKilometrage(Collections.max(this.getRefuels().stream().map(e -> e.getKilometrage())
                    .collect(Collectors.toList())));
        }
        else if (this.getRefuels().isEmpty()&&!this.getTechs().isEmpty()){
            this.setKilometrage(Collections.max(this.getTechs().stream().map(e -> e.getKilometrage())
                    .collect(Collectors.toList())));
        }
        else if (this.getTechs().isEmpty() && this.getRefuels().isEmpty()){
            this.setKilometrage(0);
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ",\n brand='" + brand + '\'' +
                ",\n model='" + model + '\'' +
                ",\n year=" + year +
                ",\n kilometrage=" + kilometrage +
                ",\n engine=" + engine +
                ",\n transmission=" + transmission +
                ",\n userId=" + userId +
                ",\n techs=" + techs +
                ",\n refuels=" + refuels +
                '}';
    }
}
