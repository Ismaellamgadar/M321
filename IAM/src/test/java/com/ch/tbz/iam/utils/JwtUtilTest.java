package com.ch.tbz.iam.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    public void testGenerateToken() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username);

        assertNotNull(token);
        assertFalse(token.isEmpty(), "Generated token should not be empty");

        assertTrue(jwtUtil.validateToken(token), "Generated token should be valid");
    }

    @Test
    public void testValidateToken_withValidToken() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username);

        assertTrue(jwtUtil.validateToken(token), "Valid token should return true");
    }

    @Test
    public void testValidateToken_withInvalidToken() {
        String invalidToken = "invalidToken";

        assertFalse(jwtUtil.validateToken(invalidToken), "Invalid token should return false");
    }
}
