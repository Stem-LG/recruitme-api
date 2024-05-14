package tn.louay.recruitme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.louay.recruitme.entities.JobOffer;
import tn.louay.recruitme.services.JobOfferService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/job-offers")
public class JobOfferController {
    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping
    public List<JobOffer> getAllJobOffers() {
        return jobOfferService.getAllJobOffers();
    }

    @GetMapping("/{id}")
    public JobOffer getJobOfferById(@PathVariable Long id) {
        return jobOfferService.getJobOfferById(id);
    }

    @PostMapping
    public JobOffer createJobOffer(@RequestBody JobOffer jobOffer) {
        return jobOfferService.createJobOffer(jobOffer);
    }

    @PutMapping("/{id}")
    public JobOffer updateJobOffer(@PathVariable Long id, @RequestBody JobOffer jobOffer) {
        return jobOfferService.updateJobOffer(jobOffer);
    }

    @DeleteMapping("/{id}")
    public void deleteJobOffer(@PathVariable Long id) {
        jobOfferService.deleteJobOffer(id);
    }

    @GetMapping("/by-recruiter/{recruiterId}")
    public List<JobOffer> getJobOffersByRecruiter(@PathVariable Long recruiterId) {
        return jobOfferService.getJobOffersByRecruiter(recruiterId);
    }
}