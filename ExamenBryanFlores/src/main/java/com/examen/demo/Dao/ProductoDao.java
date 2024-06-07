package com.examen.demo.Dao;

import org.springframework.data.repository.CrudRepository;

import com.examen.demo.Entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
