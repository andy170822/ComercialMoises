package com.comercialmoises.services;

import com.comercialmoises.dto.CompraDTO;
import java.util.List;

public interface CompraService {
    List<CompraDTO> listar();

    CompraDTO buscarPorId(Integer id);

    CompraDTO guardar(CompraDTO dto);
}
