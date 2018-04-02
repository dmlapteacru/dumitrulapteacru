package models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity(name = "project")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(unique = true)
    private int project_code;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    private Company company;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", project_code=" + project_code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", company=" + company.getId() +
                '}';
    }
}
