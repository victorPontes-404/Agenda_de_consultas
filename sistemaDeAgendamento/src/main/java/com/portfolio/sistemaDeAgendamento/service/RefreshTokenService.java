package com.portfolio.sistemaDeAgendamento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private static final String TOKEN_PREFIX = "refresh:";
    private static final String USER_PREFIX = "user_tokens:";

    private final StringRedisTemplate redis;
    private final SecureRandom random = new SecureRandom();

    @Value("${agendai.jwt.refresh-token-days}")
    private long refreshDays;

    public String create(Long userId) {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        String rawToken = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        String hash = sha256(rawToken);

        Duration ttl = Duration.ofDays(refreshDays);
        redis.opsForValue().set(TOKEN_PREFIX + hash, userId.toString(), ttl);

        String userKey = USER_PREFIX + userId;
        redis.opsForSet().add(userKey, hash);
        redis.expire(userKey, ttl);

        return rawToken;
    }

    public Optional<Long> consume(String rawToken) {
        String hash = sha256(rawToken);
        String userId = redis.opsForValue().getAndDelete(TOKEN_PREFIX + hash);
        if (userId == null) return Optional.empty();

        redis.opsForSet().remove(USER_PREFIX + userId, hash);
        return Optional.of(Long.parseLong(userId));
    }

    public void revoke(String rawToken) {
        consume(rawToken);
    }

    private String sha256(String value) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256")
                    .digest(value.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public void revokeAllFromUser(Long userId) {
        String userKey = USER_PREFIX + userId;
        Set<String> hashes = redis.opsForSet().members(userKey);
        if (hashes != null && !hashes.isEmpty()) {
            redis.delete(hashes.stream().map(h -> TOKEN_PREFIX + h).toList());
        }
        redis.delete(userKey);
    }

}
