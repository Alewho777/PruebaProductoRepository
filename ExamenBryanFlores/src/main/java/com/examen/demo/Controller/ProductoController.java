package com.examen.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.examen.demo.Entity.Producto;
import com.examen.demo.Services.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	  @Autowired
	    private ProductoService productoService;

	  @GetMapping
	    public List<Producto> getAllProductos() {
	        return productoService.findAll();
	    }

	    @GetMapping("/{id}")
	    public Producto getProductoById(@PathVariable Long id) {
	        Producto producto = productoService.findById(id);
	        if (producto == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
	        }
	        return producto;
	    }

	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public Producto createProducto(@Valid @RequestBody Producto producto) {
	        return productoService.save(producto);
	    }

	    @PutMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void updateProducto(@Valid @RequestBody Producto producto, @PathVariable Long id) {
	        Producto updateProducto = productoService.findById(id);
	        if (updateProducto == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
	        }
	        updateProducto.setDescripcion(producto.getDescripcion());
	        updateProducto.setPrecio(producto.getPrecio());
	        updateProducto.setCantidad(producto.getCantidad());
	        productoService.save(updateProducto);
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteProducto(@PathVariable Long id) {
	        Producto existingProducto = productoService.findById(id);
	        if (existingProducto == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
	        }
	        productoService.delete(id);
	    }
}
