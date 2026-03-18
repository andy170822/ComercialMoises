package com.comercialmoises.mappers;

import com.comercialmoises.dto.RolDTO;
import com.comercialmoises.model.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {
    public RolDTO toDTO(Rol rol) {
        if (rol == null)
            return null;
        return new RolDTO(rol.getIdRol(), rol.getNombreRol(), rol.getDescripcion(), rol.getEstado());
    }

    public Rol toEntity(RolDTO dto) {
        if (dto == null)
            return null;
        return new Rol(dto.getIdRol(), dto.getNombreRol(), dto.getDescripcion(), dto.getEstado());
    }
}
