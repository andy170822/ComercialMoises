package com.comercialmoises.services;

import com.comercialmoises.dto.MovimientoInventarioDTO;
import com.comercialmoises.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listar();

    ProductoDTO buscarPorId(Integer id);

    ProductoDTO guardar(ProductoDTO dto);

    ProductoDTO actualizar(Integer id, ProductoDTO dto);

    void eliminar(Integer id);

    List<MovimientoInventarioDTO> getHistorial(Integer id);
}
