package models;


import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;
import lombok.Getter;
import lombok.Setter;
import models.enums.Role;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name = "id")
    private Address address_id;

    @ManyToMany
    private List<Skills> skills;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address_id=" + address_id +
//                ", skills=" + skills +
                ", role=" + role +
                '}';
    }
}