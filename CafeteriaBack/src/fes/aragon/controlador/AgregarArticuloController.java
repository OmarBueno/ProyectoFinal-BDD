package fes.aragon.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fes.aragon.modelo.Articulo;
import fes.aragon.modelo.Articulos;
import fes.aragon.modelo.Extra;
import fes.aragon.modelo.Pedido;
import fes.aragon.mysql.ArticuloImp;
import fes.aragon.mysql.ExtraImp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.control.CheckBox;

public class AgregarArticuloController extends BaseController implements Initializable {
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
	@FXML
	private ComboBox <Articulo> cmbArticulo;
	@FXML
	private Label lblTotal;
	@FXML
	private Label lblPrecio;
	ObservableList<Articulo> grupo = Articulos.getInstancia().getGrupoArticulos();

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptarNuevo(ActionEvent event) {
			try {
				Articulo art = new Articulo();
				art=this.cmbArticulo.getValue();
				art.setCantidad(Integer.parseInt(this.txtCantidad.getText()));
				grupo.add(art);
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

	@FXML
	void setPrecio(ActionEvent event) {
		String precio = String.valueOf(((Articulo) this.cmbArticulo.getValue()).getPrecio());
		this.lblPrecio.setText(precio);
		this.setTotal(null);
	}

	@FXML
	void setTotal(KeyEvent event) {
		this.lblTotal.setText(
				String.valueOf(Double.valueOf(this.lblPrecio.getText()) * Double.valueOf(this.txtCantidad.getText())));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ArticuloImp<Articulo> cnn = new ArticuloImp<>();
			ArrayList<Articulo> articulos = cnn.consulta();
			Articulo tmpAts = new Articulo();
			tmpAts.setId(0);
			tmpAts.setNombre("Selecciona un tipo");
			this.cmbArticulo.getItems().add(tmpAts);
			for (Articulo ats : articulos) {
				this.cmbArticulo.getItems().add(ats);
			}
			ExtraImp<Extra> cnn2 = new ExtraImp<>();
			ArrayList<Extra> extras = cnn2.consulta();
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
