package tn.louay.recruitme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.louay.recruitme.entities.Skill;
import tn.louay.recruitme.services.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        return skillService.updateSkill(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}