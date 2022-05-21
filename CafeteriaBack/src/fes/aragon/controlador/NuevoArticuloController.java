package fes.aragon.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fes.aragon.modelo.Articulo;
import fes.aragon.modelo.Articulos;
import fes.aragon.modelo.Cliente;
import fes.aragon.modelo.ClientesPedidos;
import fes.aragon.modelo.Extra;
import fes.aragon.mysql.ArticuloImp;
import fes.aragon.mysql.ClienteImp;
import fes.aragon.mysql.ExtraImp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.CheckBox;

public class NuevoArticuloController extends BaseController implements Initializable {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtPrecio;
	@FXML
	private TextField txtDescripcion;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnSalir;
	@FXML
	private ComboBox<String> cmbTipoLeche;
	@FXML
	private CheckBox chkCremaBatida;
	@FXML
	private ComboBox<Extra> cmbExtra;
	@FXML
	private TextField txtCantidad;

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptarNuevo(ActionEvent event) {

		try {
			Articulo art = new Articulo();
			art.setNombre(this.txtNombre.getText());
			art.setDescripcion(this.txtDescripcion.getText());
			art.setPrecio(Double.valueOf(txtPrecio.getText()));
			art.setLeche(this.cmbTipoLeche.getValue());
			art.setCrema(this.chkCremaBatida.isSelected());
			art.setExtra(this.cmbExtra.getValue());
			art.setCantidad(Integer.parseInt(this.txtCantidad.getText()));
			ObservableList<Articulo> grupo = Articulos.getInstancia().getGrupoArticulos();
			grupo.add(art);
			ArticuloImp<Articulo> cn = new ArticuloImp<>();
			cn.insertar(art);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.cerrarVentana(btnAceptar);
	}

	// Event Listener on Button[#btnSalir].onAction
	@FXML
	public void salir(ActionEvent event) {
		this.cerrarVentana(btnSalir);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ExtraImp<Extra> cnn = new ExtraImp<>();
		try {
			ArrayList<Extra> extras = cnn.consulta();
			Extra tmpExtra = new Extra();
			tmpExtra.setId(0);
			tmpExtra.setExtra("Selecciona un tipo");
			this.cmbExtra.getItems().add(tmpExtra);
			for (Extra extra : extras) {
				this.cmbExtra.getItems().add(extra);
			}
			String[] leches = { "Selecciona un tipo", "ENTERA", "LIGHT", "DESLACTOSADA", "ALMENDRA", "SOYA", "COCO" };
			cmbTipoLeche.getItems().addAll(leches);
		} catch (Exception e) {
			e.printStackTrace();
			this.ventanaEmergente("Mensaje", "Error en la aplicación", "Consultar al administrador");
		}
	}
}
