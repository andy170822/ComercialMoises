package com.comercialmoises.mappers;

import com.comercialmoises.dto.ProductoDTO;
import com.comercialmoises.model.Producto;
import com.comercialmoises.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public ProductoDTO toDTO(Producto producto) {
        if (producto == null)
            return null;
        return new ProductoDTO(
                producto.getIdProducto(),
                producto.getCategoria() != null ? producto.getCategoria().getIdCategoria() : null,
                producto.getCategoria() != null ? producto.getCategoria().getNombreCategoria() : null,
                producto.getCodigo(),
                producto.getNombreProducto(),
                producto.getDescripcion(),
                producto.getPrecioCompra(),
                producto.getPrecioVenta(),
                producto.getStockActual(),
                producto.getStockMinimo(),
                producto.getUnidadMedida(),
                producto.getEstado());
    }

    public Producto toEntity(ProductoDTO dto) {
        if (dto == null)
            return null;
        Producto producto = new Producto();
        producto.setIdProducto(dto.getIdProducto());
        producto.setCodigo(dto.getCodigo());
        producto.setNombreProducto(dto.getNombreProducto());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStockActual(dto.getStockActual());
        producto.setStockMinimo(dto.getStockMinimo());
        producto.setUnidadMedida(dto.getUnidadMedida());
        producto.setEstado(dto.getEstado());
        if (dto.getIdCategoria() != null) {
            Categoria cat = new Categoria();
            cat.setIdCategoria(dto.getIdCategoria());
            producto.setCategoria(cat);
        }
        return producto;
    }
}
