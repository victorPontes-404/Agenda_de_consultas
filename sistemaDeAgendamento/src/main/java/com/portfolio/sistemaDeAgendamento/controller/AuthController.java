package com.portfolio.sistemaDeAgendamento.controller;

import com.portfolio.sistemaDeAgendamento.config.CookieService;
import com.portfolio.sistemaDeAgendamento.dto.body.LoginBody;
import com.portfolio.sistemaDeAgendamento.dto.body.RegisterPatientBody;
import com.portfolio.sistemaDeAgendamento.dto.response.UserResponse;
import com.portfolio.sistemaDeAgendamento.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendai/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/patient")
    public ResponseEntity<UserResponse> register(
            @RequestBody @Valid RegisterPatientBody body,
            HttpServletResponse response) {

        return new ResponseEntity<>(authService.register(body, response), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @RequestBody @Valid LoginBody body,
            HttpServletResponse response) {

        return new ResponseEntity<>(authService.login(body, response), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> refresh(
            @CookieValue(name = CookieService.REFRESH_COOKIE, required = false) String refresh,
            HttpServletResponse response) {
        authService.refresh(refresh, response);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @CookieValue(name = CookieService.REFRESH_COOKIE, required = false) String refresh,
            HttpServletResponse response) {
        authService.logout(refresh, response);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
