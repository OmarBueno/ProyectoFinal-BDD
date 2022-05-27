package fes.aragon.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Cliente;
import fes.aragon.modelo.ClientesPedidos;
import fes.aragon.mysql.ClienteImp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class NuevoClienteController extends BaseController implements Initializable {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApMaterno;
	@FXML
	private TextField txtApPaterno;
	@FXML
	private TextField txtCorreo;
	@FXML
	private TextField txtContrasena;
	@FXML
	private TextField txtTelefono;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnSalir;
	private Cliente cl;
	private String mensajes = "";

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptarNuevoUsuario(ActionEvent event) {
		if (this.verificar()) {
			try {
				if (ClientesPedidos.getInstancia().isModificarClientesPedido()) {
					cl.setNombre(this.txtNombre.getText());
					cl.setApPaterno(this.txtApPaterno.getText());
					cl.setApMaterno(this.txtApMaterno.getText());
					cl.setContrasena(this.txtContrasena.getText());
					cl.setTelefono(this.txtTelefono.getText());
					cl.setCorreo(this.txtCorreo.getText());
					ClientesPedidos.getInstancia().getGrupoClientesPedidos()
							.set(ClientesPedidos.getInstancia().getIndice(), cl);
					ClienteImp<Cliente> cn = new ClienteImp<>();
					cn.modificar(cl);
				} else {
					cl = new Cliente();
					cl.setNombre(this.txtNombre.getText());
					cl.setApPaterno(this.txtApPaterno.getText());
					cl.setApMaterno(this.txtApMaterno.getText());
					cl.setContrasena(this.txtContrasena.getText());
					cl.setTelefono(this.txtTelefono.getText());
					cl.setCorreo(this.txtCorreo.getText());
					cl.setPedidos(null);
					ObservableList<Cliente> grupo = ClientesPedidos.getInstancia().getGrupoClientesPedidos();
					grupo.add(cl);
					ClienteImp<Cliente> cn = new ClienteImp<>();
					cn.insertar(cl);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ClientesPedidos.getInstancia().setModificarClientesPedido(false);
			this.cerrarVentana(btnAceptar);
		} else {
			this.ventanaEmergente("Mensaje", "Datos no Correctos", this.mensajes);
			this.mensajes = "";
		}

	}

	// Event Listener on Button[#btnSalir].onAction
	@FXML
	public void salirNuevoUsuario(ActionEvent event) {
		ClientesPedidos.getInstancia().setModificarClientesPedido(false);
		this.cerrarVentana(btnSalir);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (ClientesPedidos.getInstancia().isModificarClientesPedido()) {
			this.cl = ClientesPedidos.getInstancia().getGrupoClientesPedidos()
					.get(ClientesPedidos.getInstancia().getIndice());
			this.txtNombre.setText(cl.getNombre());
			this.txtApPaterno.setText(cl.getApPaterno());
			this.txtApMaterno.setText(cl.getApMaterno());
			this.txtCorreo.setText(cl.getCorreo());
			this.txtContrasena.setText(cl.getContrasena());
			this.txtTelefono.setText(cl.getTelefono());
		}
	}
	
	private boolean verificar() {
		boolean valido = true;
		if ((this.txtNombre.getText() == null)
				|| (this.txtNombre.getText() != null && this.txtNombre.getText().isEmpty())) {
			this.mensajes += "El nombre del cliente no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtApPaterno.getText() == null)
				|| (this.txtApPaterno.getText() != null && this.txtApPaterno.getText().isEmpty())) {
			this.mensajes += "El apellido paterno del cliente no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtApMaterno.getText() == null)
				|| (this.txtApMaterno.getText() != null && this.txtApMaterno.getText().isEmpty())) {
			this.mensajes += "El apellido materno del cliente no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtContrasena.getText() == null)
				|| (this.txtContrasena.getText() != null && this.txtContrasena.getText().isEmpty())) {
			this.mensajes += "El apellido materno del cliente no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtCorreo.getText() == null) || (this.txtCorreo != null && this.txtCorreo.getText().isEmpty())) {
			this.mensajes += "El correo del cliente no es valido,  vacio\n";
			valido = false;
		}
		if ((this.txtCorreo.getText() == null) || (this.txtCorreo != null && !this.txtCorreo.getText().isEmpty())) {
			if (!this.correoValido) {
				this.mensajes += "El correo del cliente no es valido, esta mal estructurado\n";
				valido = false;
			}
		}
		if ((this.txtTelefono.getText() == null)
				|| (this.txtTelefono != null && this.txtTelefono.getText().isEmpty())) {
			this.mensajes += "El telefono del cliente no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtTelefono.getText() == null)
				|| (this.txtTelefono != null && !this.txtTelefono.getText().isEmpty())) {
			if (!this.telefonoValido) {
				this.mensajes += "El telefono del cliente no es valido, minimo=10,maximo=10 numeros\n";
				valido = false;
			}

		}
		return valido;
	}
}
