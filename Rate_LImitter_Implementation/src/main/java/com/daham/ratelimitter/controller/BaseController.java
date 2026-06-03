package com.daham.ratelimitter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BaseController {

    Map<String, Boolean> users = Map.of("Daham", false, "Faith", true);

    @GetMapping("/users")
    public ResponseEntity<Map<String, Boolean>> get(){
        return ResponseEntity.ok(users);
    }
}
