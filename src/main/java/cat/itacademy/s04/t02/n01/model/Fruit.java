package cat.itacademy.s04.t02.n01.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fruits")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private Double kg;

    public Fruit(){}

    public Fruit(String name, Double kg) {
        this.name = name;
        this.kg = kg;
    }

    public Fruit(int id, String name, Double kg) {
        this.id = id;
        this.name = name;
        this.kg = kg;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }
}
