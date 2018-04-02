package models;


import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;
import lombok.Getter;
import lombok.Setter;
import models.enums.Role;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

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

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany
    @JoinTable(name = "skills_employee",
            joinColumns = {@JoinColumn(name = "employees_id")},
            inverseJoinColumns = {@JoinColumn(name = "skills_id")})
    private Set<Skills> skills = new HashSet<Skills>(Arrays.asList(new Skills(), new Skills()));

    @ManyToOne
    private Company company;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address_id=" + address +
//                ", skills=" + skills.toString() +
                ", company=" + company.getId() +
                ", role=" + role +
                '}';
    }
}
