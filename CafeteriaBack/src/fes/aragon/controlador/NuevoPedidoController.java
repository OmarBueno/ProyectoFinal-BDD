package fes.aragon.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import fes.aragon.modelo.Articulo;
import fes.aragon.modelo.Articulos;
import fes.aragon.modelo.Cliente;
import fes.aragon.modelo.Extra;
import fes.aragon.modelo.Pedido;
import fes.aragon.modelo.Pedidos;
import fes.aragon.mysql.ArticuloImp;
import fes.aragon.mysql.ClienteImp;
import fes.aragon.mysql.ExtraImp;
import fes.aragon.mysql.PedidoImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

public class NuevoPedidoController extends BaseController implements Initializable {
	@FXML
	private TextField txtDireccion;
	@FXML
	private Button btnAceptarPedido;
	@FXML
	private Button btnCerrar;
	@FXML
	private ComboBox<String> cmbEstado;
	@FXML
	private ComboBox<Cliente> cmbCliente;
	@FXML
	private Label lblFecha;
	@FXML
	private Label lblTotal;
	@FXML
	private Button btnArticulos;
	Pedido pedido = Pedido.getInstancia();

	// Event Listener on Button[#btnAceptarPedido].onAction
	@FXML
	public void aceptarPedido(ActionEvent event) {
		try {
			pedido.setCliente(this.cmbCliente.getValue());
			pedido.setDireccion(this.txtDireccion.getText());
			pedido.setFecha(this.lblFecha.getText());
			pedido.setEstado(this.cmbEstado.getValue());
			pedido.setArticulos(Pedido.getInstancia().getArticulos());
			ObservableList<Pedido> grupo = Pedidos.getInstancia().getGrupoPedidos();
			grupo.add(pedido);
			PedidoImp<Pedido> cn = new PedidoImp<>();
			cn.insertar(pedido);
			System.out.println(pedido.getArticulos());
			cn.insertarArticulos(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.cerrarVentana(btnCerrar);
	}


	// Event Listener on Button[#btnCerrar].onAction
	@FXML
	public void cerrar(ActionEvent event) {
		this.cerrarVentana(btnCerrar);
	}

	// Event Listener on Button[#btnArticulos].onAction
	@FXML
	public void abrirArticulos(ActionEvent event) {
		AdministradorArticulosController.estado = true;
		this.nuevaVentana("AdministradorArticulos");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pedido.setTotal(null);
		String estados = "NUEVO";
		this.cmbEstado.getItems().add(estados);
		this.cmbEstado.getSelectionModel().select(0);
		this.cmbEstado.setDisable(true);
		SimpleDateFormat standar;
		String standarFormat;
		Calendar now = new GregorianCalendar();
		Date nowDate = now.getTime();
		standar = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		standarFormat = standar.format(nowDate);
		System.out.println(standarFormat);
		this.lblFecha.setText(standarFormat);

		ClienteImp<Cliente> cnn = new ClienteImp<>();
		try {
			ArrayList<Cliente> clientes = cnn.consulta();
			Cliente tmpCliente = new Cliente();
			tmpCliente.setId(0);
			tmpCliente.setCorreo("Selecciona un tipo");
			this.cmbCliente.getItems().add(tmpCliente);
			for (Cliente cliente : clientes) {
				this.cmbCliente.getItems().add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.ventanaEmergente("Mensaje", "Error en la aplicación", "Consultar al administrador");
		}
	}
}
