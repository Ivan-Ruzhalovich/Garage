package Garage.entity.tech;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "refuel")
public class Refuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Car_id")
    @NotNull
    private long carId;

    @Column(name = "Date")
    private String date;

    @Column(name = "Type_fuel")
    private String typeFuel;

    @Column(name = "Volume")
    private float volume;

    @Column(name = "Kilometrage")
    private int kilometrage;

    @Column(name = "Price")
    private float price;

    public Refuel() {
    }

    public Refuel(long carId) {
        this.carId = carId;
    }

    public Refuel( long carId, String date, float volume, int kilometrage, float price) {
        this.carId = carId;
        this.date = date;
        this.volume = volume;
        this.kilometrage = kilometrage;
        this.price = price;
    }

    public Refuel(float price, int kilometrage, float volume, String typeFuel,
                  String date, long carId) {
        this.price = price;
        this.kilometrage = kilometrage;
        this.volume = volume;
        this.typeFuel = typeFuel;
        this.date = date;
        this.carId = carId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeFuel() {
        return typeFuel;
    }

    public void setTypeFuel(String typeFuel) {
        this.typeFuel = typeFuel;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Refuel{" +
                "id=" + id +
                ", carId=" + carId +
                ", date='" + date + '\'' +
                ", typeFuel='" + typeFuel + '\'' +
                ", volume=" + volume +
                ", kilometrage=" + kilometrage +
                ", price=" + price +
                '}';
    }
}
