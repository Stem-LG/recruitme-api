package tn.louay.recruitme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.louay.recruitme.dto.GetApplications;
import tn.louay.recruitme.dto.PostApplication;
import tn.louay.recruitme.entities.Application;
import tn.louay.recruitme.entities.JobOffer;
import tn.louay.recruitme.entities.Recruiter;
import tn.louay.recruitme.services.ApplicationService;
import tn.louay.recruitme.services.JobOfferService;
import tn.louay.recruitme.services.PdfSummaryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/offers/{offerId}/applications")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping
    public List<GetApplications> getApplications(@PathVariable Integer offerId,
            @AuthenticationPrincipal Recruiter recruiter) {

        JobOffer jobOffer = jobOfferService.getJobOfferById(offerId);

        if (jobOffer.getRecruiter().getId() != recruiter.getId()) {
            return null;
        }

        List<Application> applications = applicationService.getApplicationsByJobOffer(jobOffer);

        List<GetApplications> getApplications = new java.util.ArrayList<GetApplications>();

        for (Application application : applications) {
            GetApplications getApplication = new GetApplications(application.getId(), application.getName(),
                    application.getStatus(),
                    application.getCreatedAt());

            getApplications.add(getApplication);
        }

        return getApplications;
    }

    @PostMapping
    public ResponseEntity<Void> createApplication(@ModelAttribute PostApplication app, @PathVariable Integer offerId) {

        String resumeSummary = "";

        try {
            resumeSummary = PdfSummaryService.generateSummary(app.getResume());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        JobOffer jobOffer = jobOfferService.getJobOfferById(offerId);

        Application application = new Application(app.getName(), app.getEmail(), app.getMotivation(), resumeSummary,
                jobOffer);

        applicationService.createApplication(application);

        return ResponseEntity.ok().build();
    }

}