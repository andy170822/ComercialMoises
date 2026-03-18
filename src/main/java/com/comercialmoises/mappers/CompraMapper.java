package com.comercialmoises.mappers;

import com.comercialmoises.dto.CompraDTO;
import com.comercialmoises.dto.DetalleCompraDTO;
import com.comercialmoises.model.Compra;
import com.comercialmoises.model.DetalleCompra;
import org.springframework.stereotype.Component;

@Component
public class CompraMapper {
    public CompraDTO toDTO(Compra compra) {
        if (compra == null)
            return null;
        CompraDTO dto = new CompraDTO();
        dto.setIdCompra(compra.getIdCompra());
        dto.setIdProveedor(compra.getProveedor().getIdProveedor());
        dto.setRazonSocialProveedor(compra.getProveedor().getRazonSocial());
        dto.setIdUsuario(compra.getUsuario().getIdUsuario());
        dto.setNombreUsuario(compra.getUsuario().getNombres() + " " + compra.getUsuario().getApellidos());
        dto.setFechaCompra(compra.getFechaCompra());
        dto.setTipoComprobante(compra.getTipoComprobante());
        dto.setSerie(compra.getSerie());
        dto.setNumeroComprobante(compra.getNumeroComprobante());
        dto.setSubTotal(compra.getSubTotal());
        dto.setIgv(compra.getIgv());
        dto.setTotal(compra.getTotal());
        dto.setEstado(compra.getEstado());
        return dto;
    }

    public DetalleCompraDTO toDetalleDTO(DetalleCompra detalle) {
        if (detalle == null)
            return null;
        return new DetalleCompraDTO(
                detalle.getIdDetalleCompra(),
                detalle.getProducto().getIdProducto(),
                detalle.getProducto().getNombreProducto(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getImporte());
    }
}
