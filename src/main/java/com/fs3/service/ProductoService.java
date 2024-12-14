package com.fs3.service;

import java.util.List;
import java.util.Optional;

import com.fs3.model.Producto;


public interface ProductoService {
    List<Producto> getAllProductos();
    List<Producto> getProductosByCategoria(String categoria);
    Optional<Producto> getProductoByID(Long id);
    Producto crearProducto(Producto producto);
    Producto actualizarProducto(Long id, Producto producto);
    void eliminarProducto(Long id);
}
