package models;


import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;
import lombok.Getter;
import lombok.Setter;
import models.enums.Role;
import models.enums.Status;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "employee")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(unique = true)
    private String userId;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "adress_employee",
            joinColumns = {@JoinColumn(name = "employees_id", unique = true)},
            inverseJoinColumns = {@JoinColumn(name = "adress_id", unique = true)})
    private Address address;

    @ManyToMany
    @JoinTable(name = "skills_employee",
            joinColumns = {@JoinColumn(name = "employees_id")},
            inverseJoinColumns = {@JoinColumn(name = "skills_id")})
    private Set<Skills> skills;

    @ManyToOne
    private Company company;

    @ManyToMany
    @JoinTable(name = "employee_projects",
                joinColumns = {@JoinColumn(name = "employee_id")},
                inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> projects;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString() {
        String newString = '{' +
                skills.stream()
                        .map(skill -> skill.getSkill())
                        .collect(Collectors.joining(" / ")) + '}';
        String projects = '{' +
                getProjects().stream()
                        .map(skill -> skill.getName())
                        .collect(Collectors.joining(" / ")) + '}';
        return "Employee{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address_id=" + address +
                ", skills=" + newString +
                ", company=" + company.getName() +
                ", projects=" + projects +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
