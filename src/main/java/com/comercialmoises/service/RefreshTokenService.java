package com.comercialmoises.service;

import com.comercialmoises.model.RefreshToken;
import com.comercialmoises.repository.RefreshTokenRepository;
import com.comercialmoises.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${jwt.refreshExpirationMs:86400000}")
    private Long refreshTokenDurationMs; // Default 24 hours

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Integer usuarioId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUsuario(usuarioRepository.findById(usuarioId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUsuarioId(Integer usuarioId) {
        return refreshTokenRepository.deleteByUsuario(usuarioRepository.findById(usuarioId).get());
    }
}
