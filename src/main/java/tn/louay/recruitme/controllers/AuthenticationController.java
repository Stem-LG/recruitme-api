package tn.louay.recruitme.controllers;

import lombok.RequiredArgsConstructor;
import tn.louay.recruitme.auth.AuthenticationResponse;
import tn.louay.recruitme.dto.RegisterRequest;
import tn.louay.recruitme.services.RecruiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final RecruiterService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

}
