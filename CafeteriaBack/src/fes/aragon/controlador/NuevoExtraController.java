package fes.aragon.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class NuevoExtraController extends BaseController implements Initializable{
	@FXML
	private TextField txtNombre;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnSalir;

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptarNuevoUsuario(ActionEvent event) {
	}
	// Event Listener on Button[#btnSalir].onAction
	@FXML
	public void salirNuevoUsuario(ActionEvent event) {
		this.cerrarVentana(btnSalir);	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.recogerDatos();		
	}
}
