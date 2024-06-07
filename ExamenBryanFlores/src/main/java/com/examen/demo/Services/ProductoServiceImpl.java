package com.examen.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.demo.Dao.ProductoDao;
import com.examen.demo.Entity.Producto;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    public Producto save(Producto producto) {
        producto.calcularValores(); 
        return productoDao.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productoDao.deleteById(id);
    }
	
}
