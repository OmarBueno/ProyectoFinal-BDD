package fes.aragon.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import static javafx.scene.control.ButtonType.OK;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import fes.aragon.modelo.Articulo;
import fes.aragon.modelo.Articulos;
import fes.aragon.modelo.Pedido;
import fes.aragon.modelo.Pedidos;
import fes.aragon.mysql.ArticuloImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class AdministradorArticulosController extends BaseController implements Initializable {
	public static boolean estado = false;
	@FXML
	private Button btnNuevoArticulo;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnEliminar;
	@FXML
	private Button btnSalir;
	@FXML
	private TableView tablaArticulos;
	@FXML
	private TableColumn<Articulo, String> clmNombre;
	@FXML
	private TableColumn<Articulo, String> clmDescripcion;
	@FXML
	private TableColumn<Articulo, Double> clmPrecio;

	private Double total = 0.0;
	

	// Event Listener on Button[#btnNuevoArticulo].onAction
	@FXML
	public void nuevoArticulo(ActionEvent event) {
		if (!estado) {
			this.nuevaVentana("NuevoArticulo");
		} else {
			this.nuevaVentana("AgregarArticulo");
		}
	}

	// Event Listener on Button[#btnEditar].onAction
	@FXML
	public void editarPedido(ActionEvent event) {
		int indice = this.tablaArticulos.getSelectionModel().getSelectedIndex();
		if (indice >= 0) {
			Articulos.getInstancia().setGrupoArticulo(true);
			Articulos.getInstancia().setIndice(indice);
			this.nuevaVentana("NuevoArticulo");
		} else {
			Alert alerta;
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Dialogo de Aviso");
			alerta.setHeaderText("Se necesita una fila");
			alerta.setContentText("Por favor selecciona una fila, para la modificar");
			Optional<ButtonType> resultado = alerta.showAndWait();
			if (resultado.get().equals(OK)) {

			}
		}
	}

	// Event Listener on Button[#btnEliminar].onAction
	@FXML
	public void eliminarArticulo(ActionEvent event) {
		int indice = this.tablaArticulos.getSelectionModel().getSelectedIndex();
		if (indice >= 0) {
			Articulos.getInstancia().setIndice(indice);
			Articulo ats = Articulos.getInstancia().getGrupoArticulos().get(Articulos.getInstancia().getIndice());
			Articulos.getInstancia().getGrupoArticulos().remove(ats);
			ArticuloImp<Articulo> cn = new ArticuloImp<>();
			try {
				cn.eliminar(ats.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert alerta;
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Dialogo de Aviso");
			alerta.setHeaderText("Se necesita una fila");
			alerta.setContentText("Por favor selecciona una fila, para eliminar");
			Optional<ButtonType> resultado = alerta.showAndWait();
			if (resultado.get().equals(OK)) {

			}
		}
	}

	// Event Listener on Button[#btnSalir].onAction
	@FXML
	public void salirArticulo(ActionEvent event) {
		ObservableList<Articulo> grupo = Articulos.getInstancia().getGrupoArticulos();
		if (estado) {
			if (Pedidos.getInstancia().isModificarPedido()) {

			} else {
				ObservableList<Articulo> grupo2 = FXCollections.observableArrayList();
				for (Articulo articulo : grupo) {
					grupo2.add(articulo);
					total += articulo.getPrecio() * articulo.getCantidad();
				}
				Pedido.getInstancia().setTotal(total);
				Pedido.getInstancia().setArticulos(grupo2);
			}
		}
		grupo.clear();
		this.cerrarVentana(btnSalir);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			if (!estado) {
				this.recogerDatosArticulos();
			} else {
				if (Pedidos.getInstancia().isModificarPedido()) {
					Pedido tmp = Pedidos.getInstancia().getGrupoPedidos().get(Pedidos.getInstancia().getIndice());
					this.recogerDatosArticulosValor(tmp.getId());
					this.btnNuevoArticulo.setDisable(true);
					this.btnEditar.setDisable(true);
					this.btnEliminar.setDisable(true);
				} else {
				}
			}
			this.clmNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			this.clmDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
			this.clmPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
			this.tablaArticulos.setItems(Articulos.getInstancia().getGrupoArticulos());
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
