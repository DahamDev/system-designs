package com.daham.ratelimitter.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Log4j2
public class HealthController {

    @RequestMapping("")
    public ResponseEntity<String> status(){
        log.debug("Health check received");
        return ResponseEntity.ok("healthy");
    }
}
