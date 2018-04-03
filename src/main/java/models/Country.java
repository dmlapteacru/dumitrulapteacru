package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Address> addresses;

    @Override
    public String toString() {
        String addresses = "";
        for (Address add: getAddresses()
             ) {
            addresses+=add.toString();
        }
        return "Country{" +
                "id=" + id +
                ", ISO_code=" + ISO_code +
                ", name='" + name + '\'' +
                ", addresses='" + addresses + '\'' +
                '}';
    }
}
