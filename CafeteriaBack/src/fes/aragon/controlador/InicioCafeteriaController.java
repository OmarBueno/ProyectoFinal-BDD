package fes.aragon.controlador;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class InicioCafeteriaController extends BaseController{
	@FXML
	private Button btnAdministrador;
	@FXML
	private Button btnCliente;
	@FXML
	private Button btnCerrar;
	

	// Event Listener on Button[#btnAdministrador].onAction
	@FXML
	public void abrirAdministrador(ActionEvent event)  {
		this.nuevaVentana("Administrador");
	}
	// Event Listener on Button[#btnCliente].onAction
	@FXML
	public void abrirCliente(ActionEvent event) {
		this.nuevaVentana("Cliente");
	}
	// Event Listener on Button[#btnCerrar].onAction
	@FXML
	public void cerrarAplicacion(ActionEvent event) {
		Platform.exit();
	}
}
