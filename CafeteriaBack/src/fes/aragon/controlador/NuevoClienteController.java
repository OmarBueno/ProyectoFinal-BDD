package fes.aragon.controlador;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import fes.aragon.modelo.Cliente;
import fes.aragon.modelo.ClientesPedidos;
import fes.aragon.mysql.ClienteImp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class NuevoClienteController extends BaseController {
	private Cliente cliente;
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApMaterno;
	@FXML
	private TextField txtApPaterno;
	@FXML
	private TextField txtCorreo;
	@FXML
	private TextField txtContraseña;
	@FXML
	private TextField txtTelefono;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnSalir;

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptarNuevoUsuario(ActionEvent event) {
		try {
			Cliente cl = new Cliente();
			cl.setNombre(this.txtNombre.getText());
			cl.setApPaterno(this.txtApPaterno.getText());
			cl.setApMaterno(this.txtApMaterno.getText());
			cl.setContrasena(this.txtContraseña.getText());
			cl.setTelefono(this.txtTelefono.getText());
			cl.setCorreo(this.txtCorreo.getText());
			cl.setPedidos(null);
			ObservableList<Cliente> grupo = ClientesPedidos.getInstancia().getGrupoClientesPedidos();
			grupo.add(cl);
			ClienteImp<Cliente> cn = new ClienteImp<>();
			cn.insertar(cl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.cerrarVentana(btnAceptar);

	}

	// Event Listener on Button[#btnSalir].onAction
	@FXML
	public void salirNuevoUsuario(ActionEvent event) {
		this.cerrarVentana(btnSalir);
	}
}
