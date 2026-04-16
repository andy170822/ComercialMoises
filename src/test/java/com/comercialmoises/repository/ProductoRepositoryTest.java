package com.comercialmoises.repository;

import com.comercialmoises.model.Categoria;
import com.comercialmoises.model.Producto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("ProductoRepository Data JPA Tests")
class ProductoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductoRepository repository;

    @Test
    @DisplayName("Should save and find product")
    void testSaveAndFind() {
        Categoria cat = new Categoria();
        cat.setNombreCategoria("Herramientas");
        entityManager.persist(cat);

        Producto prod = new Producto();
        prod.setNombreProducto("Martillo");
        prod.setCodigo("MART-001");
        prod.setPrecioCompra(new BigDecimal("10.00"));
        prod.setPrecioVenta(new BigDecimal("15.00"));
        prod.setStockActual(10);
        prod.setCategoria(cat);
        prod.setEstado(true);

        Producto saved = repository.save(prod);
        entityManager.flush();

        Optional<Producto> found = repository.findById(saved.getIdProducto());
        assertTrue(found.isPresent());
        assertEquals("Martillo", found.get().getNombreProducto());
    }
}
