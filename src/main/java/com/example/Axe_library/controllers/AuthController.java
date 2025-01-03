package com.example.Axe_library.controllers;

import com.example.Axe_library.dto.AuthenticationRequest;
import com.example.Axe_library.dto.AuthenticationResponse;
import com.example.Axe_library.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest){

            AuthenticationResponse jwtDto = authenticationService.login(authRequest);
            return ResponseEntity.ok(jwtDto);
    }

    @GetMapping("/public-access")
    public String endpointPublico(){
        return "endpoint publico";
    }
}
