package com.ecommerce.config;

import com.ecommerce.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("---- JWT FILTER START ----");
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            System.out.println("Token: " + token);
            try {
                String username = jwtService.extractUsername(token);
                System.out.println("Username: " + username);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    boolean isValid = jwtService.isTokenValid(token, username);
                    System.out.println("Token valid: " + isValid);
                    if (isValid) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                username, null, Collections.emptyList()
                        );
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        System.out.println("Authentication SET: " +
                                SecurityContextHolder.getContext().getAuthentication());
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request,response);
    }
}
