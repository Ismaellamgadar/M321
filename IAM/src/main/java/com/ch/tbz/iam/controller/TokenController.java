package com.ch.tbz.iam.controller;

import com.ch.tbz.iam.entity.AppUser;
import com.ch.tbz.iam.entity.UserCredentials;
import com.ch.tbz.iam.repository.UserRepository;
import com.ch.tbz.iam.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class TokenController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials credentials) {
        System.out.println("lol");
        Optional<AppUser> userOptional = userRepository.findByUsername(credentials.getUsername());

        if (userOptional.isPresent()) {
            AppUser appUser = userOptional.get();

            if (appUser.getPassword().equals(credentials.getPassword())) {
                String token = jwtUtil.generateToken(appUser.getUsername());

                // Return token wrapped in a JSON object
                return ResponseEntity.ok(Map.of("token", token));
            }
        }

        // Return an error message wrapped in a JSON object
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid username or password"));
    }


    // backend will call this endpoint to check if token is valid
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        return ResponseEntity.ok(jwtUtil.validateToken(token));
    }
}
