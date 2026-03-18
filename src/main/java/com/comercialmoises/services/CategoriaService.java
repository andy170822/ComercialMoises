package com.comercialmoises.services;

import com.comercialmoises.dto.CategoriaDTO;
import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> listar();

    CategoriaDTO buscarPorId(Integer id);

    CategoriaDTO guardar(CategoriaDTO dto);

    CategoriaDTO actualizar(Integer id, CategoriaDTO dto);

    void eliminar(Integer id);
}
