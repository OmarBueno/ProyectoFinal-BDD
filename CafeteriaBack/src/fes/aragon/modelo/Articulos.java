package fes.aragon.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Articulos {
	private static Articulos instancia = new Articulos();
	private ObservableList<Articulo> grupoArticulos = FXCollections.observableArrayList();
	private boolean grupoArticulo = false;
	private int indice = -1;
	private int indiceArticulo = -1;

	public Articulos() {
		// TODO Auto-generated constructor stub
	}

	public static Articulos getInstancia() {
		return instancia;
	}

	public static void setInstancia(Articulos instancia) {
		Articulos.instancia = instancia;
	}

	public ObservableList<Articulo> getGrupoArticulos() {
		return grupoArticulos;
	}

	public void setGrupoArticulos(ObservableList<Articulo> grupoArticulos) {
		this.grupoArticulos = grupoArticulos;
	}

	public boolean isGrupoArticulo() {
		return grupoArticulo;
	}

	public void setGrupoArticulo(boolean grupoArticulo) {
		this.grupoArticulo = grupoArticulo;
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
