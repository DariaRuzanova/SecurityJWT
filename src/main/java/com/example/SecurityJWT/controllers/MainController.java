package com.example.SecurityJWT.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/unsecured")
    public String unsecuredDate(){
        return "Unsecured data";

    }
    @GetMapping("/secured")
    public String securedDate(){
        return "Secured data";
    }

    @GetMapping("/admin")
    public String adminDate(){
        return "Admin data";
    }
    @GetMapping("/info")
    public String userDate(Principal principal){
        return principal.getName();
    }
}
