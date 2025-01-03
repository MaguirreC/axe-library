package com.example.Axe_library.services;

import com.example.Axe_library.dto.AuthenticationRequest;
import com.example.Axe_library.dto.AuthenticationResponse;
import com.example.Axe_library.models.User;
import com.example.Axe_library.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());

        authenticationManager.authenticate(authenticationToken);

        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(()-> new RuntimeException("user not found"));

        String jwt = jwtService.generateToken(user,generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }

    private Map<String,Object> generateExtraClaims(User user) {
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("permissions",user.getAuthorities());
        return extraClaims;
    }

}
