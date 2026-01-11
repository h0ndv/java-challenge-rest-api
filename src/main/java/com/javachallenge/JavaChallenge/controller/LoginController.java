package com.javachallenge.JavaChallenge.controller;
import com.javachallenge.JavaChallenge.dto.LoginDTO;
import com.javachallenge.JavaChallenge.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final TokenService tokenService;

    public LoginController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
        String token = tokenService.generarToken(dto.getUsername());
        return ResponseEntity.ok(token);
    }
}
