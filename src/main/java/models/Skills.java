package models;

import lombok.Getter;
import lombok.Setter;
import models.enums.Type;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    private List<Employee> employees;

    @Override
    public String toString() {
        return "Skills{" +
                "id=" + id +
                ", type=" + type +
                ", skill='" + skill + '\'' +
                '}';
    }
}
