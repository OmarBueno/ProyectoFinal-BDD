package fes.aragon.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pedidos {
	private static Pedidos instancia = new Pedidos();
	private ObservableList<Pedido> grupoPedidos = FXCollections.observableArrayList();
	private boolean modificarPedido = false;
	private int indice = -1;
	private int indiceArticulo = -1;

	public Pedidos() {
		// TODO Auto-generated constructor stub
	}

	public static Pedidos getInstancia() {
		return instancia;
	}

	public static void setInstancia(Pedidos instancia) {
		Pedidos.instancia = instancia;
	}

	public ObservableList<Pedido> getGrupoPedidos() {
		return grupoPedidos;
	}

	public void setGrupoPedidos(ObservableList<Pedido> grupoPedidos) {
		this.grupoPedidos = grupoPedidos;
	}

	public boolean isModificarPedido() {
		return modificarPedido;
	}

	public void setModificarPedido(boolean modificarPedido) {
		this.modificarPedido = modificarPedido;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getIndiceArticulo() {
		return indiceArticulo;
	}

	public void setIndiceArticulo(int indiceArticulo) {
		this.indiceArticulo = indiceArticulo;
	}

}
