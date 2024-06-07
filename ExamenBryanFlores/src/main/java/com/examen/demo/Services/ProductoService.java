package com.examen.demo.Services;

import java.util.List;

import com.examen.demo.Entity.Producto;

public interface ProductoService {
	List<Producto> findAll();
    Producto save(Producto producto);
    Producto findById(Long id);
    void delete(Long id);
}
