package com.ch.tbz.backend.filter;

import com.ch.tbz.backend.iam.IAMClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private IAMClient iamClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Extract the token from the header

            // Validate token by calling IAM service
            Boolean isValid = iamClient.validateToken(token);

            if (!isValid) {
                // If token is invalid, set the response status to 401 Unauthorized
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
        }

        // Continue with the filter chain if the token is valid or if no token is provided
        filterChain.doFilter(request, response);
    }
}
