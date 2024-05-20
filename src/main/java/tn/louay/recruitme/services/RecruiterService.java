package tn.louay.recruitme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.louay.recruitme.auth.AuthenticationResponse;
import tn.louay.recruitme.config.jwt.JwtService;
import tn.louay.recruitme.dto.RegisterRequest;
import tn.louay.recruitme.entities.Recruiter;
import tn.louay.recruitme.entities.Role;
import tn.louay.recruitme.repositories.RecruiterRepository;

import java.util.*;

@Service
public class RecruiterService implements UserDetailsService {
    @Autowired
    private final RecruiterRepository recruiterRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RecruiterService(RecruiterRepository recruiterRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService, AuthenticationManager authenticationManager) {
        this.recruiterRepository = recruiterRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return recruiterRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found: " + username));
    }

    public AuthenticationResponse register(RegisterRequest request) {

        var user = Recruiter.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        recruiterRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(RegisterRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = recruiterRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    public Recruiter getRecruiterById(Integer id) {
        return recruiterRepository.findById(id).orElseThrow();
    }

    public Recruiter createRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    public Recruiter updateRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    public void deleteRecruiter(Integer id) {
        recruiterRepository.deleteById(id);
    }
}