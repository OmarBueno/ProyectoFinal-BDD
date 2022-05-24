package fes.aragon.modelo;

import java.io.Serializable;

public class Articulo implements Serializable {
	private Integer id;
	private String nombre;
	private String descripcion;
	private Double precio;
	private Integer cantidad;

	public Articulo() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
