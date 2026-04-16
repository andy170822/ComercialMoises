package com.comercialmoises.mappers;

import com.comercialmoises.dto.CotizacionDTO;
import com.comercialmoises.dto.DetalleCotizacionDTO;
import com.comercialmoises.model.Cotizacion;
import com.comercialmoises.model.DetalleCotizacion;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.Collections;

@Component
public class CotizacionMapper {
    public CotizacionDTO toDTO(Cotizacion entity) {
        if (entity == null) return null;
        CotizacionDTO dto = new CotizacionDTO();
        dto.setIdCotizacion(entity.getIdCotizacion());
        dto.setNumeroCotizacion(entity.getNumeroCotizacion());
        if (entity.getCliente() != null) {
            dto.setIdCliente(entity.getCliente().getIdCliente());
            dto.setNombreCliente(entity.getCliente().getRazonSocial() != null && !entity.getCliente().getRazonSocial().isEmpty() 
                ? entity.getCliente().getRazonSocial() 
                : entity.getCliente().getNombres() + " " + entity.getCliente().getApellidos());
        }
        if (entity.getUsuario() != null) {
            dto.setIdUsuario(entity.getUsuario().getIdUsuario());
        }
        dto.setFechaEmision(entity.getFechaEmision());
        dto.setFechaVencimiento(entity.getFechaVencimiento());
        dto.setTotal(entity.getTotal());
        dto.setEstado(entity.getEstado());
        if (entity.getDetalles() != null) {
            dto.setDetalles(entity.getDetalles().stream().map(this::detalleToDTO).collect(Collectors.toList()));
        } else {
            dto.setDetalles(Collections.emptyList());
        }
        return dto;
    }

    public DetalleCotizacionDTO detalleToDTO(DetalleCotizacion entity) {
        if (entity == null) return null;
        DetalleCotizacionDTO dto = new DetalleCotizacionDTO();
        dto.setIdDetalleCotizacion(entity.getIdDetalleCotizacion());
        if (entity.getProducto() != null) {
            dto.setIdProducto(entity.getProducto().getIdProducto());
            dto.setNombreProducto(entity.getProducto().getNombreProducto());
        }
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        dto.setSubtotal(entity.getSubtotal());
        return dto;
    }
}
