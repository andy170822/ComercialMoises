package com.comercialmoises.controller;

import com.comercialmoises.dto.AuthRequest;
import com.comercialmoises.dto.AuthResponse;
import com.comercialmoises.dto.TokenRefreshRequest;
import com.comercialmoises.dto.TokenRefreshResponse;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.model.RefreshToken;
import com.comercialmoises.model.Usuario;
import com.comercialmoises.repository.UsuarioRepository;
import com.comercialmoises.security.CustomUserDetailsService;
import com.comercialmoises.security.JwtUtil;
import com.comercialmoises.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        System.out.println("DEBUG: Recibida peticion de autenticacion para: " + authRequest.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            System.out.println("DEBUG: Autenticacion exitosa para: " + authRequest.getUsername());
        } catch (BadCredentialsException e) {
            System.out.println("DEBUG: Credenciales incorrectas para: " + authRequest.getUsername());
            throw new Exception("Incorrect username or password", e);
        } catch (Exception e) {
            System.out.println("DEBUG: Error inesperado en autenticacion: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);
        final String role = userDetails.getAuthorities().iterator().next().getAuthority();

        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuario.getIdUsuario());

        return ResponseEntity.ok(new AuthResponse(jwt, refreshToken.getToken(), userDetails.getUsername(), role));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsuario)
                .map(usuario -> {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getUsername());
                    String token = jwtUtil.generateToken(userDetails);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
    }
}
