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

    public List<JobOffer> getAllJobOffers() {
        return jobOfferRepository.findAll();
    }

    public JobOffer getJobOfferById(Integer id) {
        return jobOfferRepository.findById(id).orElseThrow();
    }

    public List<JobOffer> getJobOffersByRecruiter(Recruiter recruiter) {
        return jobOfferRepository.findByRecruiter(recruiter);
    }

    public JobOffer createJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    public JobOffer updateJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    public void deleteJobOffer(Integer id) {
        jobOfferRepository.deleteById(id);
    }

}