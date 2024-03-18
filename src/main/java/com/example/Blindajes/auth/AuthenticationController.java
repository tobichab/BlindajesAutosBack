package com.example.Blindajes.auth;

import com.example.Blindajes.config.JwtService;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws ResourceNotFoundException {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUserInfo(@RequestHeader("Authorization") String token) throws ResourceNotFoundException {
        String jwtToken = token.substring(7); // Elimina el prefijo "Bearer "
        String username = jwtService.extractUsername(jwtToken); // Implementa el método para extraer el username del token
        log.info(username);
        return ResponseEntity.ok(authenticationService.getUserInfo(username));
    }

    @GetMapping("/token")
    public ResponseEntity<Boolean> isTokenExpired(@RequestBody String token){
        return ResponseEntity.ok(jwtService.isTokenExpired(token));
    }

}
