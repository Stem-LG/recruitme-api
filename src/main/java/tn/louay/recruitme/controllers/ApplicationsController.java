package tn.louay.recruitme.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.louay.recruitme.dto.GetApplication;
import tn.louay.recruitme.dto.PutApplication;
import tn.louay.recruitme.entities.Application;
import tn.louay.recruitme.entities.Recruiter;
import tn.louay.recruitme.enums.ApplicationAction;
import tn.louay.recruitme.enums.ApplicationStatus;
import tn.louay.recruitme.services.ApplicationService;

@RestController
@RequestMapping("/applications/{id}")
public class ApplicationsController {

    ApplicationService applicationService;

    @GetMapping
    public GetApplication getApplication(@PathVariable Integer id, @AuthenticationPrincipal Recruiter recruiter) {

        Application application = applicationService.getApplicationById(id);

        if (application.getJobOffer().getRecruiter().getId() != recruiter.getId()) {
            return null;
        }

        GetApplication getApplication = new GetApplication(
                application.getId(),
                application.getCreatedAt(),
                application.getJobOffer().getTitle(),
                application.getStatus(),
                application.getName(),
                application.getEmail(),
                application.getMotivation(),
                application.getResumeSummary());

        return getApplication;

    }

    @PutMapping
    public void updateApplication(@PathVariable Integer id, @AuthenticationPrincipal Recruiter recruiter,
            @ModelAttribute PutApplication action) {

        Application application = applicationService.getApplicationById(id);

        if (application.getJobOffer().getRecruiter().getId() != recruiter.getId()) {
            return;
        }

        if (action.getAction() == ApplicationAction.accept) {
            application.setStatus(ApplicationStatus.accepted);
        } else {
            application.setStatus(ApplicationStatus.rejected);
        }

        applicationService.updateApplication(application);

    }
}
