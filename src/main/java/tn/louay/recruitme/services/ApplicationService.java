package tn.louay.recruitme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.louay.recruitme.entities.Application;
import tn.louay.recruitme.repositories.ApplicationRepository;
import java.util.*;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElseThrow();
    }

    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    public Application updateApplication(Application application) {
        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}
