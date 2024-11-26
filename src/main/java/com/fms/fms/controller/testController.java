package com.fms.fms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {
	
    @GetMapping("/hello")
    public String sayHello(@AuthenticationPrincipal UserDetails userDetails) {
        // Return a simple message with the username from the authenticated user
        return "Hello, " + (userDetails != null ? userDetails.getUsername() : "Guest") + "!";
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo(@AuthenticationPrincipal UserDetails userDetails) {
        // Return a JSON response with user details
        Map<String, Object> response = new HashMap<>();
        response.put("username", userDetails != null ? userDetails.getUsername() : "Guest");
        response.put("message", "This is a secured endpoint.");
        return response;
    }

}
