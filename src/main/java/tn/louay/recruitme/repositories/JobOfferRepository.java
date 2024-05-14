package tn.louay.recruitme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.louay.recruitme.entities.JobOffer;
import tn.louay.recruitme.entities.Recruiter;
import java.util.*;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    List<JobOffer> findByRecruiter(Recruiter recruiter);
}
