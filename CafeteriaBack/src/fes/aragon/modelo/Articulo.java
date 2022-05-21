package fes.aragon.modelo;

import java.io.Serializable;

public class Articulo implements Serializable {
	private Integer id;
	private String nombre;
	private String descripcion;
	private Double precio;
	private String leche;
	private Extra extra;
	private Integer cantidad;
	private boolean crema;

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

	public Extra getExtra() {
		return extra;
	}

	public void setExtra(Extra extra) {
		this.extra = extra;
	}

	public boolean isCrema() {
		return crema;
	}

	public void setCrema(boolean crema) {
		this.crema = crema;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getLeche() {
		return leche;
	}

	public void setLeche(String leche) {
		this.leche = leche;
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", leche=" + leche
				+ ", extra=" + extra + ", crema=" + crema + "]";
	}

}
