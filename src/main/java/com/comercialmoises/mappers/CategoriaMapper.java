package com.comercialmoises.mappers;

import com.comercialmoises.dto.CategoriaDTO;
import com.comercialmoises.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null)
            return null;
        return new CategoriaDTO(
                categoria.getIdCategoria(),
                categoria.getNombreCategoria(),
                categoria.getDescripcion(),
                categoria.getEstado());
    }

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null)
            return null;
        return new Categoria(
                dto.getIdCategoria(),
                dto.getNombreCategoria(),
                dto.getDescripcion(),
                dto.getEstado());
    }
}
