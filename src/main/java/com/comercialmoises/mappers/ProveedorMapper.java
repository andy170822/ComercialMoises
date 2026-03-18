package com.comercialmoises.mappers;

import com.comercialmoises.dto.ProveedorDTO;
import com.comercialmoises.model.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {
    public ProveedorDTO toDTO(Proveedor proveedor) {
        if (proveedor == null)
            return null;
        return new ProveedorDTO(
                proveedor.getIdProveedor(),
                proveedor.getRazonSocial(),
                proveedor.getRuc(),
                proveedor.getTelefono(),
                proveedor.getEmail(),
                proveedor.getDireccion(),
                proveedor.getEstado());
    }

    public Proveedor toEntity(ProveedorDTO dto) {
        if (dto == null)
            return null;
        return new Proveedor(
                dto.getIdProveedor(),
                dto.getRazonSocial(),
                dto.getRuc(),
                dto.getTelefono(),
                dto.getEmail(),
                dto.getDireccion(),
                dto.getEstado());
    }
}
