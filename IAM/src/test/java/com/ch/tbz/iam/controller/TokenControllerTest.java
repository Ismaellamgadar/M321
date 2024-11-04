package com.ch.tbz.iam.controller;

import com.ch.tbz.iam.entity.AppUser;
import com.ch.tbz.iam.entity.UserCredentials;
import com.ch.tbz.iam.repository.UserRepository;
import com.ch.tbz.iam.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TokenControllerTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TokenController tokenController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_success() {
        UserCredentials credentials = new UserCredentials("testUser", "testPassword");
        AppUser appUser = new AppUser();
        appUser.setUsername("testUser");
        appUser.setPassword("testPassword");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(appUser));
        when(jwtUtil.generateToken("testUser")).thenReturn("fake-jwt-token");

        ResponseEntity<?> response = tokenController.login(credentials);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Map.of("token", "fake-jwt-token"), response.getBody());

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(jwtUtil, times(1)).generateToken("testUser");
    }

    @Test
    public void testLogin_invalidPassword() {
        UserCredentials credentials = new UserCredentials("testUser", "wrongPassword");
        AppUser appUser = new AppUser();
        appUser.setUsername("testUser");
        appUser.setPassword("testPassword");        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(appUser));

        ResponseEntity<?> response = tokenController.login(credentials);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(Map.of("message", "Invalid username or password"), response.getBody());

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(jwtUtil, never()).generateToken(anyString());
    }

    @Test
    public void testLogin_userNotFound() {
        UserCredentials credentials = new UserCredentials("nonexistentUser", "anyPassword");
        when(userRepository.findByUsername("nonexistentUser")).thenReturn(Optional.empty());

        ResponseEntity<?> response = tokenController.login(credentials);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(Map.of("message", "Invalid username or password"), response.getBody());

        verify(userRepository, times(1)).findByUsername("nonexistentUser");
        verify(jwtUtil, never()).generateToken(anyString());
    }

    @Test
    public void testValidateToken_validToken() {
        String validToken = "valid-token";
        when(jwtUtil.validateToken(validToken)).thenReturn(true);

        ResponseEntity<Boolean> response = tokenController.validateToken(validToken);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());

        verify(jwtUtil, times(1)).validateToken(validToken);
    }

    @Test
    public void testValidateToken_invalidToken() {
        String invalidToken = "invalid-token";
        when(jwtUtil.validateToken(invalidToken)).thenReturn(false);

        ResponseEntity<Boolean> response = tokenController.validateToken(invalidToken);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(false, response.getBody());

        verify(jwtUtil, times(1)).validateToken(invalidToken);
    }
}
