package com.sinem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FallbackController {
    @GetMapping("/fallback-auth-service")
    public ResponseEntity<String > fallbackAuthService(){
        return ResponseEntity.ok("Auth servise ulasılamadı.");
    }
    @GetMapping("/fallback-user-service")
    public ResponseEntity<String > fallbackUserService(){
        return ResponseEntity.ok("User servise ulasılamadı.");
    }
}
