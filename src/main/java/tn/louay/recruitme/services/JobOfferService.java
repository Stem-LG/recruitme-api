package tn.louay.recruitme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.louay.recruitme.entities.JobOffer;
import tn.louay.recruitme.entities.Recruiter;
import tn.louay.recruitme.repositories.JobOfferRepository;
import java.util.*;

@Service
public class JobOfferService {
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private RecruiterService recruiterService;

    public List<JobOffer> getAllJobOffers() {
        return jobOfferRepository.findAll();
    }

    public JobOffer getJobOfferById(Long id) {
        return jobOfferRepository.findById(id).orElseThrow();
    }

    public JobOffer createJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    public JobOffer updateJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    public void deleteJobOffer(Long id) {
        jobOfferRepository.deleteById(id);
    }

    public List<JobOffer> getJobOffersByRecruiter(Long recruiterId) {
        Recruiter recruiter = recruiterService.getRecruiterById(recruiterId);
        return jobOfferRepository.findByRecruiter(recruiter);
    }
}