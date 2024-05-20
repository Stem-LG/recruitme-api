package tn.louay.recruitme.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.louay.recruitme.dto.GetOffers;
import tn.louay.recruitme.entities.JobOffer;
import tn.louay.recruitme.entities.Recruiter;
import tn.louay.recruitme.services.JobOfferService;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping("/offers")
    public List<GetOffers> getRecruiterOffers(@AuthenticationPrincipal Recruiter recruiter) {

        List<JobOffer> jobOffers = jobOfferService.getJobOffersByRecruiter(recruiter);

        List<GetOffers> getOffers = new ArrayList<GetOffers>();

        for (JobOffer jobOffer : jobOffers) {
            GetOffers getOffer = new GetOffers(jobOffer.getId(), jobOffer.getTitle(), jobOffer.getCreatedAt());
            getOffers.add(getOffer);
        }

        return getOffers;
    }

}