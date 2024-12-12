package com.fs3.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductoTest {

    @Test
    public void testConstructorVacio() {
        Producto producto = new Producto();
        assertEquals(null, producto.getId());
        assertEquals(null, producto.getNombre());
        assertEquals(null, producto.getDescripcion());
        assertEquals(0, producto.getPrecio());
        assertEquals(null, producto.getTipoProducto());
        assertEquals(null, producto.getUrlImg());
    }

    @Test
    public void testConstructorConParametros() {
        Long id = 1L;
        String nombre = "Nombre";
        String descripcion = "Descripción";
        int precio = 10;
        String tipoProducto = "Categoría";
        String urlImg = "Url Imagen";

        Producto producto = new Producto(id, nombre, descripcion, precio, tipoProducto, urlImg);
        assertEquals(id, producto.getId());
        assertEquals(nombre, producto.getNombre());
        assertEquals(descripcion, producto.getDescripcion());
        assertEquals(precio, producto.getPrecio());
        assertEquals(tipoProducto, producto.getTipoProducto());
        assertEquals(urlImg, producto.getUrlImg());
    }

    @Test
    public void testGettersYSetters() {
        Producto producto = new Producto();
        Long id = 1L;
        String nombre = "Nombre";
        String descripcion = "Descripción";
        int precio = 10;
        String tipoProducto = "Categoría";
        String urlImg = "Url Imagen";

        producto.setId(id);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setTipoProducto(tipoProducto);
        producto.setUrlImg(urlImg);

        assertEquals(id, producto.getId());
        assertEquals(nombre, producto.getNombre());
        assertEquals(descripcion, producto.getDescripcion());
        assertEquals(precio, producto.getPrecio());
        assertEquals(tipoProducto, producto.getTipoProducto());
        assertEquals(urlImg, producto.getUrlImg());
    }

}