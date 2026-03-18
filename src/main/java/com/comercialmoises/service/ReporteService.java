package com.comercialmoises.service;

import com.comercialmoises.dto.DashboardStatsDTO;
import com.comercialmoises.dto.ProductoMasVendidoDTO;
import com.comercialmoises.model.Producto;
import com.comercialmoises.model.Venta;
import com.comercialmoises.repository.ClienteRepository;
import com.comercialmoises.repository.ProductoRepository;
import com.comercialmoises.repository.VentaRepository;
import com.comercialmoises.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

        @Autowired
        private VentaRepository ventaRepository;

        @Autowired
        private ProductoRepository productoRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private GastoRepository gastoRepository;

        public DashboardStatsDTO getDashboardStats() {
                DashboardStatsDTO stats = new DashboardStatsDTO();

                // Sumar total de ventas (simplificado a sumar todas por ahora como demo)
                BigDecimal totalVentas = ventaRepository.findAll().stream()
                                .map(v -> v.getTotal() != null ? v.getTotal() : BigDecimal.ZERO)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                // Sumar gastos
                BigDecimal totalGastos = gastoRepository.findAll().stream()
                                .map(g -> g.getMonto() != null ? g.getMonto() : BigDecimal.ZERO)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                stats.setVentasDelMes(totalVentas);
                stats.setCajaActual(totalVentas.subtract(totalGastos)); // Simplificado

                stats.setClientesActivos(clienteRepository.findAll().size());

                long stockBajoCount = productoRepository.findAll().stream()
                                .filter(p -> p.getStockActual() <= p.getStockMinimo())
                                .count();
                stats.setStockBajo((int) stockBajoCount);

                return stats;
        }

        public List<ProductoMasVendidoDTO> getProductosMasVendidos() {
                return ventaRepository.findTopSellingProducts(PageRequest.of(0, 5));
        }

        public List<Producto> getProductosConStockBajo() {
                return productoRepository.findAll().stream()
                                .filter(p -> p.getStockActual() != null && p.getStockMinimo() != null
                                                && p.getStockActual() <= p.getStockMinimo())
                                .collect(Collectors.toList());
        }
}
