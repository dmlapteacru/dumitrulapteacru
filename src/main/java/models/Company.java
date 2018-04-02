package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "company")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Project> projects;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", projects=" + projects +
//                ", employees=" + employees +
                '}';
    }
}
