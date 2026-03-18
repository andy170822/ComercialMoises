package com.comercialmoises.mappers;

import com.comercialmoises.dto.VentaDTO;
import com.comercialmoises.dto.DetalleVentaDTO;
import com.comercialmoises.model.Venta;
import com.comercialmoises.model.DetalleVenta;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {
    public VentaDTO toDTO(Venta venta) {
        if (venta == null)
            return null;
        VentaDTO dto = new VentaDTO();
        dto.setIdVenta(venta.getIdVenta());
        dto.setIdCliente(venta.getCliente().getIdCliente());
        dto.setNombreCliente(venta.getCliente().getNombres() != null
                ? venta.getCliente().getNombres() + " " + venta.getCliente().getApellidos()
                : venta.getCliente().getRazonSocial());
        dto.setIdUsuario(venta.getUsuario().getIdUsuario());
        dto.setNombreUsuario(venta.getUsuario().getNombres() + " " + venta.getUsuario().getApellidos());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setTipoComprobante(venta.getTipoComprobante());
        dto.setSerie(venta.getSerie());
        dto.setNumeroComprobante(venta.getNumeroComprobante());
        dto.setSubTotal(venta.getSubTotal());
        dto.setIgv(venta.getIgv());
        dto.setTotal(venta.getTotal());
        dto.setEstado(venta.getEstado());
        return dto;
    }

    public DetalleVentaDTO toDetalleDTO(DetalleVenta detalle) {
        if (detalle == null)
            return null;
        return new DetalleVentaDTO(
                detalle.getIdDetalleVenta(),
                detalle.getProducto().getIdProducto(),
                detalle.getProducto().getNombreProducto(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getDescuento(),
                detalle.getImporte());
    }
}
