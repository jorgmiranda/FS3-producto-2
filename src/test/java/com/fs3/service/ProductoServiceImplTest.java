package com.fs3.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.fs3.model.Producto;
import com.fs3.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;

    @BeforeEach
    public void setup() {
        producto = new Producto(1L, "Nombre", "Descripción", 10, "Categoría", "Url Imagen");
    }

    @Test
    public void testGetAllProductos() {
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);

        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> result = productoService.getAllProductos();

        assertEquals(1, result.size());
        assertEquals(producto, result.get(0));
    }

    @Test
    public void testGetProductoByID() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> result = productoService.getProductoByID(1L);

        assertNotNull(result);
        assertEquals(producto, result.get());
    }

    @Test
    public void testGetProductoByIDNotFound() {
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Producto> result = productoService.getProductoByID(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testCrearProducto() {
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto result = productoService.crearProducto(producto);

        assertEquals(producto, result);
    }

    @Test
    public void testActualizarProducto() {
        when(productoRepository.existsById(1L)).thenReturn(true);
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto result = productoService.actualizarProducto(1L, producto);

        assertEquals(producto, result);
    }

    @Test
    public void testActualizarProductoNotFound() {
        when(productoRepository.existsById(1L)).thenReturn(false);

        Producto result = productoService.actualizarProducto(1L, producto);

        assertNull(result);
    }

    @Test
    public void testEliminarProducto() {
        productoService.eliminarProducto(1L);

        // No hay nada que verificar, solo se llama al método
    }

    @Test
    public void testGetProductosByCategoria() {
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);

        when(productoRepository.findByTipoProducto("Categoría")).thenReturn(productos);

        List<Producto> result = productoService.getProductosByCategoria("Categoría");

        assertEquals(1, result.size());
        assertEquals(producto, result.get(0));
    }
}