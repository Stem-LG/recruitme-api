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

import tn.louay.recruitme.entities.Recruiter;
import tn.louay.recruitme.services.RecruiterService;

@RestController
@RequestMapping("/recruiters")
public class RecruiterController {
    @Autowired
    private RecruiterService recruiterService;

    @GetMapping
    public List<Recruiter> getAllRecruiters() {
        return recruiterService.getAllRecruiters();
    }

    @GetMapping("/{id}")
    public Recruiter getRecruiterById(@PathVariable Long id) {
        return recruiterService.getRecruiterById(id);
    }

    @PostMapping
    public Recruiter createRecruiter(@RequestBody Recruiter recruiter) {
        return recruiterService.createRecruiter(recruiter);
    }

    @PutMapping("/{id}")
    public Recruiter updateRecruiter(@PathVariable Long id, @RequestBody Recruiter recruiter) {
        return recruiterService.updateRecruiter(recruiter);
    }

    @DeleteMapping("/{id}")
    public void deleteRecruiter(@PathVariable Long id) {
        recruiterService.deleteRecruiter(id);
    }
}