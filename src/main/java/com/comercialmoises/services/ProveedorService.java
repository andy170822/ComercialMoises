package com.comercialmoises.services;

import com.comercialmoises.dto.ProveedorDTO;
import java.util.List;

public interface ProveedorService {
    List<ProveedorDTO> listar();

    ProveedorDTO buscarPorId(Integer id);

    ProveedorDTO guardar(ProveedorDTO dto);

    ProveedorDTO actualizar(Integer id, ProveedorDTO dto);

    void eliminar(Integer id);
}
