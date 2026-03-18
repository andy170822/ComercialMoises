package com.comercialmoises.security;

import com.comercialmoises.model.Usuario;
import com.comercialmoises.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Intento de login para usuario: {}", username);
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("Usuario no encontrado: {}", username);
                    return new UsernameNotFoundException("Usuario no encontrado: " + username);
                });

        log.info("Usuario encontrado: {}, Estado: {}, Rol: {}", usuario.getUsername(), usuario.getEstado(),
                usuario.getRol().getNombreRol());

        if (!usuario.getEstado()) {
            log.warn("Usuario inactivo: {}", username);
            throw new UsernameNotFoundException("Usuario inactivo");
        }

        return new User(
                usuario.getUsername(),
                usuario.getPasswordHash(),
                Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombreRol().toUpperCase())));
    }
}
