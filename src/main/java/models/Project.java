package models;

import lombok.Getter;
import lombok.Setter;
import models.enums.Status;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employeeSet;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString() {
        String employees = '{' +
                employeeSet.stream()
                        .map(skill -> skill.getUserId())
                        .collect(Collectors.joining(" / ")) + '}';
        return "Project{" +
                "id=" + id +
                ", project_code=" + project_code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", company=" + company.getName() +
                ", employees=" + employees +
                ", status=" + status +
                '}';
    }
}
