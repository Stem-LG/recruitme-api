package tn.louay.recruitme.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.louay.recruitme.enums.ApplicationStatus;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String motivation;
    @Column(columnDefinition = "TEXT")
    private String resumeSummary;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    private Recruiter recruiter;

    @ManyToOne
    private JobOffer jobOffer;

    public Application(String name, String email, String motivation, String resumeSummary,
            JobOffer jobOffer) {
        this.name = name;
        this.email = email;
        this.motivation = motivation;
        this.resumeSummary = resumeSummary;
        this.createdAt = new Date();
        this.status = ApplicationStatus.pending;
        this.jobOffer = jobOffer;
    }
}