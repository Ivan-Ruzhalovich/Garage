package Garage.entity.tech;

import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "tech_car")
public class TechCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Car_id")
    @NotNull
    private long carId;

    @OneToOne()
    @JoinColumn(name = "Work_id")
    @NotNull
    private Works work;

    @Column(name ="Date")
    private String date;

    @Column (name = "Kilometrage")
    private int kilometrage;

    @Column(name = "Price")
    private int price;

    transient private Map<Integer,String> works;

    public TechCar() {

    }

    public TechCar(long carId, Works work, String date, int kilometrage, int price) {
        this.carId = carId;
        this.work = work;
        this.date = date;
        this.kilometrage = kilometrage;
        this.price = price;
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

    public Works getWork() {
        return work;
    }

    public void setWork(Works work) {
        this.work = work;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Map<Integer,String> getWorks() {
        return works;
    }

    public void setWorks(Map<Integer,String> works) {
        this.works = works;
    }

    @Override
    public String toString() {
        return "TechCar{" +
                "id=" + id +
                ", carId=" + carId +
                ", work=" + work +
                ", date=" + date +
                ", kilometrage=" + kilometrage +
                ", price=" + price +
                '}';
    }


}
