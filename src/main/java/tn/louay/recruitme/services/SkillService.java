package tn.louay.recruitme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.louay.recruitme.entities.Skill;
import tn.louay.recruitme.repositories.SkillRepository;
import java.util.*;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElseThrow();
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
