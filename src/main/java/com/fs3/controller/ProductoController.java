package com.fs3.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fs3.model.Producto;
import com.fs3.service.ProductoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/productos")
public class ProductoController {
    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductoByID(@PathVariable Long id) {
        Optional<Producto> producto = productoService.getProductoByID(id);
        if (producto.isEmpty()) {
            log.error("No se encontro ningun Producto con ese ID {} ", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontro ningun Producto con ese ID"));
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/tipo/{categoria}")
    public List<Producto> getProductoByCategoria(@PathVariable String categoria) {
        return productoService.getProductosByCategoria(categoria);
    }


    @PostMapping
    public ResponseEntity<Object> crearProducto(@RequestBody Producto producto){

        Producto p = productoService.crearProducto(producto);
        if(p == null){
            log.error("Error al crear el producto");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error al crear el producto"));
        }
        return ResponseEntity.ok(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        Optional<Producto> productoOPT = productoService.getProductoByID(id);
        if (productoOPT.isEmpty()) {
            log.error("No se encontro ningun Producto con ese ID {} ", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontro ningun Producto con ese ID"));
        }

        Producto p = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarProducto(@PathVariable Long id){
        Optional<Producto> productoOPT = productoService.getProductoByID(id);
        if (productoOPT.isEmpty()) {
            log.error("No se encontro ningun Producto con ese ID {} ", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontro ningun Producto con ese ID"));
        }

        productoService.eliminarProducto(id);
        // return ResponseEntity.ok("Producto eliminado");
        return ResponseEntity.noContent().build();
    }


    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message){
            this.message = message;
        }
    
        public String getMessage(){
            return message;
        }
        
    }
}
