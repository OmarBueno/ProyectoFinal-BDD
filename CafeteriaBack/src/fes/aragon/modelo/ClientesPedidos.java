package fes.aragon.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientesPedidos {
	private static ClientesPedidos instancia = new ClientesPedidos();
	private ObservableList<Cliente> grupoClientesPedidos = FXCollections.observableArrayList();
	private boolean modificarClientesPedido = false;
	private int indice = -1;
	private int indicePedido = -1;

	public ClientesPedidos() {
		// TODO Auto-generated constructor stub
	}

	public static ClientesPedidos getInstancia() {
		return instancia;
	}

	public static void setInstancia(ClientesPedidos instancia) {
		ClientesPedidos.instancia = instancia;
	}

	public ObservableList<Cliente> getGrupoClientesPedidos() {
		return grupoClientesPedidos;
	}

	public void setGrupoClientesPedidos(ObservableList<Cliente> grupoClientesPedidos) {
		this.grupoClientesPedidos = grupoClientesPedidos;
	}

	public boolean isModificarClientesPedido() {
		return modificarClientesPedido;
	}

	public void setModificarClientesPedido(boolean modificarClientesPedido) {
		this.modificarClientesPedido = modificarClientesPedido;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getIndicePedido() {
		return indicePedido;
	}

	public void setIndicePedido(int indicePedido) {
		this.indicePedido = indicePedido;
	}

}
