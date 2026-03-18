package com.comercialmoises.services;

import com.comercialmoises.dto.RolDTO;
import java.util.List;

public interface RolService {
    List<RolDTO> listar();

    RolDTO buscarPorId(Integer id);

    RolDTO guardar(RolDTO dto);

    RolDTO actualizar(Integer id, RolDTO dto);

    void eliminar(Integer id);
}
