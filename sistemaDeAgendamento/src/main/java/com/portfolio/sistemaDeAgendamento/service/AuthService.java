package com.portfolio.sistemaDeAgendamento.service;

import com.portfolio.sistemaDeAgendamento.Repository.UserRepository;
import com.portfolio.sistemaDeAgendamento.config.CookieService;
import com.portfolio.sistemaDeAgendamento.config.TokenService;
import com.portfolio.sistemaDeAgendamento.dto.body.LoginBody;
import com.portfolio.sistemaDeAgendamento.dto.body.RegisterPatientBody;
import com.portfolio.sistemaDeAgendamento.dto.response.UserResponse;
import com.portfolio.sistemaDeAgendamento.entity.Address;
import com.portfolio.sistemaDeAgendamento.entity.User;
import com.portfolio.sistemaDeAgendamento.entity.UserPatient;
import com.portfolio.sistemaDeAgendamento.exception.AuthException;
import com.portfolio.sistemaDeAgendamento.exception.ConflictException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.portfolio.sistemaDeAgendamento.dto.body.AddressBody.toAddress;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;
    private final CookieService cookieService;
    private final AuthenticationManager authenticationManager;


    @Transactional
    public UserResponse register(RegisterPatientBody body, HttpServletResponse response) {
        if (userRepository.existsByEmail(body.email())) {
            throw new ConflictException("E-mail já cadastrado");
        }

        if (userRepository.existsByCpf(body.cpf())) {
            throw new ConflictException("CPF já cadastrado");
        }

        Address address = toAddress(body.address());

        User patient = new UserPatient(
                body.email(),
                body.phone(),
                body.cpf(),
                body.name(),
                body.birthDate()
        );

        patient.setPassword(passwordEncoder.encode(body.password()));
        patient.setAddress(address);
        userRepository.save(patient);
        issueTokens(patient, response);
        return UserResponse.from(patient);
    }

    public UserResponse login(LoginBody body, HttpServletResponse response) {
        User user = userRepository.findByEmail(body.email())
                .orElseThrow(() -> new AuthException("credenciais invalidas"));

        if (!user.isEnabled()) {
            throw new AuthException("conta desativada");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            body.email(),
                            body.password()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new AuthException("credenciais invalidas");
        }

        issueTokens(user, response);
        return UserResponse.from(user);
    }

    public void refresh(String rawRefreshToken, HttpServletResponse response) {
        if (rawRefreshToken == null) {
            throw new AuthException("Refresh token ausente");
        }

        Long userId = refreshTokenService.consume(rawRefreshToken)
                .orElseThrow(() -> new AuthException("Refresh token inválido ou expirado"));

        User user = userRepository.findById(userId)
                .filter(User::isEnabled)
                .orElseThrow(() -> new AuthException("Usuário não encontrado ou inativo"));

        issueTokens(user, response);
    }

    public void logout(String rawRefreshToken, HttpServletResponse response) {
        if (rawRefreshToken != null) {
            refreshTokenService.revoke(rawRefreshToken);
        }
        cookieService.clear(response);
    }

    private void issueTokens(User user, HttpServletResponse response) {
        cookieService.addAccessCookie(response, tokenService.generateAccessToken(user));
        cookieService.addRefreshCookie(response, refreshTokenService.create(user.getId()));
    }

}
