package com.comercialmoises.mappers;

import com.comercialmoises.dto.ClienteDTO;
import com.comercialmoises.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null)
            return null;
        return new ClienteDTO(
                cliente.getIdCliente(),
                cliente.getTipoDocumento(),
                cliente.getNumeroDocumento(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getRazonSocial(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getDireccion(),
                cliente.getEstado());
    }

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null)
            return null;
        return new Cliente(
                dto.getIdCliente(),
                dto.getTipoDocumento(),
                dto.getNumeroDocumento(),
                dto.getNombres(),
                dto.getApellidos(),
                dto.getRazonSocial(),
                dto.getTelefono(),
                dto.getEmail(),
                dto.getDireccion(),
                dto.getEstado());
    }
}
