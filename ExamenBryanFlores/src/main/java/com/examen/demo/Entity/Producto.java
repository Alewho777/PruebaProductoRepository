package com.examen.demo.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final BigDecimal IVA_RATE = BigDecimal.valueOf(0.12);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal precio;

    @NotNull
    @Min(1)
    private Integer cantidad;

    private BigDecimal subtotal;
    private BigDecimal descuento;
    private BigDecimal iva;
    private BigDecimal total;

    @PrePersist
    @PreUpdate
    public void calcularValores() {
        this.subtotal = this.precio.multiply(BigDecimal.valueOf(this.cantidad));
        if (this.subtotal.compareTo(BigDecimal.valueOf(50)) > 0) {
            this.descuento = this.subtotal.multiply(BigDecimal.valueOf(0.10));
        } else {
            this.descuento = BigDecimal.ZERO;
        }
        this.iva = (this.subtotal.subtract(this.descuento)).multiply(BigDecimal.valueOf(0.12));
        this.total = this.subtotal.subtract(this.descuento).add(this.iva);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	
    
}
