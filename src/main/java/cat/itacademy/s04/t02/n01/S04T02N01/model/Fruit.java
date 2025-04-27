package cat.itacademy.s04.t02.n01.S04T02N01.model;

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
    private double kg;

    public Fruit(){}

    public Fruit(String name, int kg) {
        this.name = name;
        this.kg = kg;
    }

    public Fruit(int id, String name, double kg) {
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

    public double getKg() {
        return kg;
    }

    public void setKg(double kg) {
        this.kg = kg;
    }
}
