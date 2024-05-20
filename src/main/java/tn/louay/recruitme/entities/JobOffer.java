package tn.louay.recruitme.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;

    private String skills;

    private String Company;
    private Date createdAt;

    @ManyToOne
    private Recruiter recruiter;

    public JobOffer(String title, String description, String skills, String company, Recruiter recruiter) {
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.Company = company;
        this.recruiter = recruiter;
        this.createdAt = new Date();
    }
}