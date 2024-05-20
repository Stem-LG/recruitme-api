package tn.louay.recruitme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.louay.recruitme.entities.Application;
import tn.louay.recruitme.entities.JobOffer;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByJobOffer(JobOffer jobOffer);
}
