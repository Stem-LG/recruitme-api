package tn.louay.recruitme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.louay.recruitme.entities.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
