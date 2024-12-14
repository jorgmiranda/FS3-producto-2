package com.fs3.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fs3.model.Producto;
import com.fs3.service.ProductoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Test
    public void testGetAllProductos() {
        // Configuración
        List<Producto> productos = List.of(new Producto(1L, "Nombre", "Descripción", 10, "Categoría", "Url Imagen"));
        when(productoService.getAllProductos()).thenReturn(productos);

        // Ejecución
        List<Producto> response = productoController.getAllProductos();

        // Verificación
        assertEquals(productos, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetProductoByID() {
        // Configuración
        Producto producto = new Producto(1L, "Nombre", "Descripción", 10, "Categoría", "Url Imagen");
        when(productoService.getProductoByID(anyLong())).thenReturn(Optional.of(producto));

        // Ejecución
        ResponseEntity<Object> response = productoController.getProductoByID(1L);

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(producto, ((Optional<Producto>) response.getBody()).get());
    }

    @Test
    public void testGetProductoByIDNotFound() {
        // Configuración
        when(productoService.getProductoByID(anyLong())).thenReturn(Optional.empty());

        // Ejecución
        ResponseEntity<Object> response = productoController.getProductoByID(1L);

        // Verificación
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetProductoByCategoria() {
        // Configuración
        List<Producto> productos = List.of(new Producto(1L, "Nombre", "Descripción", 10, "Categoría", "Url Imagen"));
        when(productoService.getProductosByCategoria(any())).thenReturn(productos);

        // Ejecución
        List<Producto> response =  productoController.getProductoByCategoria("Categoría");

        // Verificación
        assertEquals(productos, response);
    }

    @Test
    public void testCrearProducto() {
        // Configuración
        Producto producto = new Producto(1L, "Nombre", "Descripción", 10, "Categoría", "Url Imagen");
        when(productoService.crearProducto(any())).thenReturn(producto);

        // Ejecución
        ResponseEntity<Object> response = productoController.crearProducto(producto);

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(producto, response.getBody());
    }

    @Test
    public void testCrearProductoError() {
        // Configuración
        when(productoService.crearProducto(any())).thenReturn(null);

        // Ejecución
        ResponseEntity<Object> response = productoController.crearProducto(new Producto());

        // Verificación
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testActualizarProducto() {
        // Configuración
        Producto producto = new Producto(1L, "Nombre", "Descripción", 10, "Categoría", "Url Imagen");
        when(productoService.getProductoByID(anyLong())).thenReturn(Optional.of(producto));
        when(productoService.actualizarProducto(anyLong(), any())).thenReturn(producto);

        // Ejecución
        ResponseEntity<Object> response = productoController.actualizarProducto(1L, producto);

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(producto, response.getBody());
    }

    @Test
    public void testActualizarProductoNotFound() {
        // Configuración
        when(productoService.getProductoByID(anyLong())).thenReturn(Optional.empty());

        // Ejecución
        ResponseEntity<Object> response = productoController.actualizarProducto(1L, new Producto());

        // Verificación
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}