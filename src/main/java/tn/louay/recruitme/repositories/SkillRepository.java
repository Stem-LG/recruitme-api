package tn.louay.recruitme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.louay.recruitme.entities.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
