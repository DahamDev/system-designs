package com.daham.ratelimitter.service;


import com.daham.ratelimitter.Entitiy.TokenBucket;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Qualifier
public class TokenBucketRateLimiterService implements RateLimiter {

    private final Map<String, Integer> rateLimitRules = Map.of(
            "GET:/api/v1/users",10,
            "GET:/health",5
    );

    private final Map<String, TokenBucket> tokenBuckets = createTokenBuckets();

    private Map<String, TokenBucket> createTokenBuckets() {
        long now = System.currentTimeMillis();

        return rateLimitRules.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new TokenBucket(
                                entry.getValue(),
                                entry.getValue(),
                                entry.getKey(),
                                now
                        )
                ));
    }

    @Override
    public boolean allowRequest(String requestURI, String method) {
        return tokenBuckets.get(method+":"+requestURI).getToken();
    }
}
