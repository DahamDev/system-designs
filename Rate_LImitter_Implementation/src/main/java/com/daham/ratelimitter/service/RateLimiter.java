package com.daham.ratelimitter.service;

public interface RateLimiter {
    boolean allowRequest(String requestURI, String method);
}
