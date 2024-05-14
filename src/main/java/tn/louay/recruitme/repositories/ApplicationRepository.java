package tn.louay.recruitme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.louay.recruitme.entities.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
