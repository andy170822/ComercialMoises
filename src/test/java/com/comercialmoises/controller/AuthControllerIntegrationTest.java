package com.comercialmoises.controller;

import com.comercialmoises.dto.AuthRequest;
import com.comercialmoises.model.Rol;
import com.comercialmoises.model.Usuario;
import com.comercialmoises.repository.RolRepository;
import com.comercialmoises.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("AuthController Integration Tests")
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private com.comercialmoises.repository.RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        refreshTokenRepository.deleteAll();
        usuarioRepository.deleteAll();
        rolRepository.deleteAll();

        Rol adminRol = new Rol();
        adminRol.setNombreRol("ADMIN");
        adminRol = rolRepository.save(adminRol);

        Usuario user = new Usuario();
        user.setUsername("testuser");
        user.setPasswordHash("password");
        user.setNombres("Test");
        user.setApellidos("User");
        user.setRol(adminRol);
        user.setEstado(true);
        usuarioRepository.save(user);
    }

    @Test
    @DisplayName("Should authenticate successfully and return JWT")
    void testAuthenticate() throws Exception {
        AuthRequest authRequest = new AuthRequest("testuser", "password");

        mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.role").value("ROLE_ADMIN"));
    }

    @Test
    @DisplayName("Should fail authentication with wrong credentials")
    void testAuthenticateFailure() throws Exception {
        AuthRequest authRequest = new AuthRequest("testuser", "wrongpassword");

        mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isInternalServerError()); // AuthController throws Exception on failure
    }
}
