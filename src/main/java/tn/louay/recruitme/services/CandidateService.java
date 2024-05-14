package tn.louay.recruitme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.louay.recruitme.entities.Candidate;
import tn.louay.recruitme.repositories.CandidateRepository;
import java.util.*;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElseThrow();
    }

    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}
