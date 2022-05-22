package fes.aragon.controlador;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

public class AdministradorAceptarController extends BaseController {
	@FXML
	private Button btnClientes;
	@FXML
	private Button btnPedidos;
	@FXML
	private Button btnArticulos;
	@FXML
	private Button btnExtras;
	@FXML
	private Button btnSalir;

	// Event Listener on Button[#btnClientes].onAction
	@FXML
	public void datosClientes(ActionEvent event) {
		this.nuevaVentana("AdministradorCliente");
	}

	// Event Listener on Button[#btnPedidos].onAction
	@FXML
	public void datosPedidos(ActionEvent event) {
		this.nuevaVentana("AdministradorPedidos");
	}

	// Event Listener on Button[#btnArticulos].onAction
	@FXML
	public void datosArticulos(ActionEvent event) {
		this.nuevaVentana("AdministradorArticulos");
	}

	@FXML
	void datosExtras(ActionEvent event) {
		this.nuevaVentana("AdministradorExtra");
	}

	@FXML
	void salir(ActionEvent event) {
		this.cerrarVentana(btnSalir);
	}
}
