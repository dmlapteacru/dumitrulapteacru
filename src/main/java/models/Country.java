package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "country")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int ISO_code;

    @Column
    private String name;

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", ISO_code=" + ISO_code +
                ", name='" + name + '\'' +
                '}';
    }
}
