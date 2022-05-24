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

import fes.aragon.modelo.Pedido;
import fes.aragon.modelo.Pedidos;
import fes.aragon.mysql.PedidoImp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class AdministradorPedidosController extends BaseController implements Initializable {
	@FXML
	private Button btnNuevoPedido;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnEliminar;
	@FXML
	private Button btnSalir;
	@FXML
	private TableView<Pedido> tablaPedidos;
	@FXML
	private TableColumn<Pedido, Integer> clmId;
	@FXML
	private TableColumn<Pedido, String> clmDireccion;
	@FXML
	private TableColumn<Pedido, String> clmFecha;
	@FXML
	private TableColumn<Pedido, String> clmEstado;
	@FXML
	private TableColumn<Pedido, Double> clmTotal;
	@FXML
	private TableColumn<Pedido, String> clmCliente;

	// Event Listener on Button[#btnNuevoPedido].onAction
	@FXML
	public void nuevoPedido(ActionEvent event) {
	this.nuevaVentana("NuevoPedido");
	}

	// Event Listener on Button[#btnEditar].onAction
	@FXML
	public void editarPedido(ActionEvent event) {
		int indice = this.tablaPedidos.getSelectionModel().getSelectedIndex();
		if (indice >= 0) {
			Pedidos.getInstancia().setModificarPedido(true);
			Pedidos.getInstancia().setIndice(indice);
			this.nuevaVentana("NuevoPedido");
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
	public void eliminarPedido(ActionEvent event) {
		int indice = this.tablaPedidos.getSelectionModel().getSelectedIndex();
		if (indice >= 0) {
			Pedidos.getInstancia().setIndice(indice);
			Pedido pedido = Pedidos.getInstancia().getGrupoPedidos()
					.get(Pedidos.getInstancia().getIndice());
			Pedidos.getInstancia().getGrupoPedidos().remove(pedido);
			PedidoImp<Pedido> cn = new PedidoImp<>();
			try {
				cn.eliminar(pedido.getId());
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
	public void salirPedido(ActionEvent event) {
		ObservableList<Pedido> grupo = Pedidos.getInstancia().getGrupoPedidos();
		grupo.clear();
		this.cerrarVentana(btnSalir);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.recogerDatosPedidos();
			ObservableList<Pedido> grupo = Pedidos.getInstancia().getGrupoPedidos();
			for (Pedido pedido : grupo) {
				System.out.println(pedido);
			}
			this.clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
			this.clmDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
			this.clmFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			this.clmEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
			this.clmTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
			this.clmCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
			this.tablaPedidos.setItems(Pedidos.getInstancia().getGrupoPedidos());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
