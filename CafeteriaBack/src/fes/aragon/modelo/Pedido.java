package fes.aragon.modelo;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pedido implements Serializable {
	private Integer id;
	private Cliente cliente = new Cliente();
	private String direccion;
	private String fecha;
	private String estado;
	private Double total;
	private ObservableList<Articulo> articulos = FXCollections.observableArrayList();
	private static Pedido instancia = new Pedido();

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public ObservableList<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(ObservableList<Articulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", cliente=" + cliente + ", direccion=" + direccion + ", fecha=" + fecha
				+ ", estado=" + estado + ", total=" + total + ", articulos=" + articulos + "]";
	}

}
