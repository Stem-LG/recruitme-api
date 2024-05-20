package tn.louay.recruitme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.louay.recruitme.dto.GetOffer;
import tn.louay.recruitme.dto.GetOffers;
import tn.louay.recruitme.dto.PostOffer;
import tn.louay.recruitme.entities.JobOffer;
import tn.louay.recruitme.entities.Recruiter;
import tn.louay.recruitme.services.JobOfferService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class JobOfferController {
    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping
    public List<GetOffers> getAllJobOffers() {

        List<JobOffer> jobOffers = jobOfferService.getAllJobOffers();

        List<GetOffers> getOffers = new ArrayList<GetOffers>();

        for (JobOffer jobOffer : jobOffers) {
            GetOffers getOffer = new GetOffers(jobOffer.getId(), jobOffer.getTitle(), jobOffer.getCreatedAt());
            getOffers.add(getOffer);
        }

        return getOffers;
    }

    @GetMapping("/{id}")
    public GetOffer getJobOfferById(@PathVariable Integer id) {

        JobOffer jobOffer = jobOfferService.getJobOfferById(id);

        GetOffer getOffer = new GetOffer(jobOffer.getId(), jobOffer.getTitle(), jobOffer.getCreatedAt(),
                jobOffer.getCompany(), jobOffer.getSkills(), jobOffer.getDescription());

        return getOffer;

    }

    @PostMapping
    public ResponseEntity<Void> createJobOffer(@RequestBody PostOffer offer,
            @AuthenticationPrincipal Recruiter recruiter) {

        JobOffer jobOffer = new JobOffer(offer.getTitle(), offer.getDescription(), offer.getSkills(),
                offer.getCompany(), recruiter);

        jobOfferService.createJobOffer(jobOffer);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJobOffer(@PathVariable Integer id, @RequestBody PostOffer offer,
            @AuthenticationPrincipal Recruiter recruiter) {

        JobOffer jobOffer = jobOfferService.getJobOfferById(id);

        if (!jobOffer.getRecruiter().equals(recruiter)) {
            return ResponseEntity.status(403).build();
        }

        jobOffer.setTitle(offer.getTitle());
        jobOffer.setDescription(offer.getDescription());
        jobOffer.setSkills(offer.getSkills());
        jobOffer.setCompany(offer.getCompany());

        jobOfferService.updateJobOffer(jobOffer);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobOffer(@PathVariable Integer id, @AuthenticationPrincipal Recruiter recruiter) {

        JobOffer jobOffer = jobOfferService.getJobOfferById(id);

        if (!jobOffer.getRecruiter().equals(recruiter)) {
            return ResponseEntity.status(403).build();
        }

        jobOfferService.deleteJobOffer(id);

        return ResponseEntity.ok().build();
    }
}