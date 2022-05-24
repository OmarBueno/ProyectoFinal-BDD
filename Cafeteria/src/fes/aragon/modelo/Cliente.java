package fes.aragon.modelo;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cliente implements Serializable {
	private Integer id;
	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private String correo;
	private String contrasena;
	private String telefono;
	private ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
	private static Cliente instancia = new Cliente();

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
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

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public ObservableList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ObservableList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public static Cliente getInstancia() {
		return instancia;
	}

	public static void setInstancia(Cliente instancia) {
		Cliente.instancia = instancia;
	}

	@Override
	public String toString() {
		return correo;
	}

}
