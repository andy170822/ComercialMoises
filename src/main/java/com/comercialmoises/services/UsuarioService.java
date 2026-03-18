package com.comercialmoises.services;

import com.comercialmoises.dto.UsuarioDTO;
import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> listar();

    UsuarioDTO buscarPorId(Integer id);

    UsuarioDTO guardar(UsuarioDTO dto);

    UsuarioDTO actualizar(Integer id, UsuarioDTO dto);

    void eliminar(Integer id);
}
