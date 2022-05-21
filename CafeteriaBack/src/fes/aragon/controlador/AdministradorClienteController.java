package fes.aragon.controlador;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Cliente;
import fes.aragon.modelo.ClientesPedidos;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdministradorClienteController extends BaseController implements Initializable{

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnNuevoCliente;

	@FXML
	private Button btnSalir;

	@FXML
	private TableColumn<Cliente, String> clmApMaterno;

	@FXML
	private TableColumn<Cliente, String> clmApPaterno;

	@FXML
	private TableColumn<Cliente, String> clmCorreo;

	@FXML
	private TableColumn<Cliente, String> clmNombre;

	@FXML
	private TableColumn<Cliente, String> clmTellefono;

	@FXML
	private TableView<Cliente> tablaClientes;

	@FXML
	void editarCliente(ActionEvent event) {

	}

	@FXML
	void eliminarCliente(ActionEvent event) {

	}

	@FXML
	void nuevoCliente(ActionEvent event) {
		this.nuevaVentana("NuevoCliente");
	}

	@FXML
	void salirCliente(ActionEvent event) {
		ObservableList<Cliente> grupo=ClientesPedidos.getInstancia().getGrupoClientesPedidos();
		grupo.clear();
		this.cerrarVentana(btnSalir);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.recogerDatos();
			ObservableList<Cliente> grupo=ClientesPedidos.getInstancia().getGrupoClientesPedidos();
			for (Cliente cliente : grupo) {
				System.out.println(cliente);
			}
			this.clmNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			this.clmApPaterno.setCellValueFactory(new PropertyValueFactory<>("apPaterno"));
			this.clmApMaterno.setCellValueFactory(new PropertyValueFactory<>("apMaterno"));
			this.clmCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
			this.clmTellefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
			this.tablaClientes.setItems(ClientesPedidos.getInstancia().getGrupoClientesPedidos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
