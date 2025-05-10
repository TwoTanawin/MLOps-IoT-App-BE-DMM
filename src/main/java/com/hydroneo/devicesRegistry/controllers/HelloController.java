package com.hydroneo.devicesRegistry.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public ResponseEntity<String> hello(){
        String message = "Hello Device Registry";
        return ResponseEntity.ok(message);
    }
}
