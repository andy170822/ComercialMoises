package com.comercialmoises.services;

import com.comercialmoises.dto.VentaDTO;
import java.util.List;

public interface VentaService {
    List<VentaDTO> listar();

    VentaDTO buscarPorId(Integer id);

    VentaDTO guardar(VentaDTO dto);
}
