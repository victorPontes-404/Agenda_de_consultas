package com.portfolio.sistemaDeAgendamento.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CookieService {

    public static final String ACCESS_COOKIE = "access_token";
    public static final String REFRESH_COOKIE = "refresh_token";

    @Value("${agendai.cookie.secure}")
    private boolean secure;

    @Value("${agendai.cookie.same-site}")
    private String sameSite;

    @Value("${agendai.jwt.refresh-token-days}")
    private long refreshDays;

    private final TokenService tokenService;

    public CookieService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void addAccessCookie(HttpServletResponse res, String token) {
        add(res, build(ACCESS_COOKIE, token, "/", Duration.ofSeconds(tokenService.accessTokenSeconds())));
    }

    public void addRefreshCookie(HttpServletResponse res, String token) {
        add(res, build(REFRESH_COOKIE, token, "/agendai/auth", Duration.ofDays(refreshDays)));
    }

    public void clear(HttpServletResponse res) {
        add(res, build(ACCESS_COOKIE, "", "/", Duration.ZERO));
        add(res, build(REFRESH_COOKIE, "", "/agendai/auth", Duration.ZERO));
    }

    private ResponseCookie build(String name, String value, String path, Duration maxAge) {
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(secure)
                .sameSite(sameSite)
                .path(path)
                .maxAge(maxAge)
                .build();
    }

    private void add(HttpServletResponse res, ResponseCookie cookie) {
        res.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

}
