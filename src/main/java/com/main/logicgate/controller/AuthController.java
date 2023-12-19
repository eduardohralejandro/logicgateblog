package com.main.logicgate.controller;

import com.main.logicgate.configuration.AuthService;
import com.main.logicgate.dto.AuthenticationResponseDTO;
import com.main.logicgate.dto.AuthenticationrRequestDTO;
import com.main.logicgate.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody AuthenticationrRequestDTO request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));

    }
}
