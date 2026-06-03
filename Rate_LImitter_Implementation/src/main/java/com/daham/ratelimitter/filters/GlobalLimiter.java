package com.daham.ratelimitter.filters;

import com.daham.ratelimitter.service.RateLimiter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class GlobalLimiter extends OncePerRequestFilter {

    private final RateLimiter rateLimiter;

    public GlobalLimiter(RateLimiter rateLimiter){
        this.rateLimiter = rateLimiter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (rateLimiter.allowRequest(request.getRequestURI(), request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }
        response.setStatus(429);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"Too many requests\"}");

    }
}
