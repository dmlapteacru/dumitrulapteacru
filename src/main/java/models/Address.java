package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Country country;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private int street_num;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", street_num=" + street_num +
                '}';
    }
}
