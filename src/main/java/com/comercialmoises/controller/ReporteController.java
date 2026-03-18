package com.comercialmoises.controller;

import com.comercialmoises.dto.DashboardStatsDTO;
import com.comercialmoises.dto.ProductoMasVendidoDTO;
import com.comercialmoises.model.Producto;
import com.comercialmoises.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/dashboard")
    public DashboardStatsDTO getDashboardStats() {
        return reporteService.getDashboardStats();
    }

    @GetMapping("/top-productos")
    public List<ProductoMasVendidoDTO> getTopProductos() {
        return reporteService.getProductosMasVendidos();
    }

    @GetMapping("/stock-bajo")
    public List<Producto> getStockBajo() {
        return reporteService.getProductosConStockBajo();
    }
}
