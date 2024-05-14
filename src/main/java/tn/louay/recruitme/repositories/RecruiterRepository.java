package tn.louay.recruitme.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.louay.recruitme.entities.Recruiter;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    Optional<Recruiter> findByEmail(String email);

}