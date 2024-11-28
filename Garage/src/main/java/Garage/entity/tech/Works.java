package Garage.entity.tech;


import javax.persistence.*;

@Entity
@Table(name = "tech_work")
public class Works {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Description")
    private String description;

    public Works() {
    }

    public Works(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Works{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
