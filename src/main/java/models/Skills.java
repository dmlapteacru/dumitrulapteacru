package models;

import lombok.Getter;
import lombok.Setter;
import models.enums.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "skills")
@Getter
@Setter
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    private String skill;

    @ManyToMany(mappedBy = "skills")
    private Set<Employee> employees;

    @Override
    public String toString() {
        return "Skills{" +
                "id=" + id +
                ", type=" + type +
                ", skill='" + skill + '\'' +
                '}';
    }
}
