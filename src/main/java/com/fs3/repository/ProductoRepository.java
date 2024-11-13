package com.fs3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fs3.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
}
